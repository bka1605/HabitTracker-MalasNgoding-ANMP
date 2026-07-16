package com.anmp.habittracker_malasngoding_anmp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anmp.habittracker_malasngoding_anmp.R
import com.anmp.habittracker_malasngoding_anmp.databinding.FragmentHabitBinding
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.viewmodel.CreateHabitViewModel

class HabitFragment : Fragment() {
    private lateinit var binding: FragmentHabitBinding
    private lateinit var viewModel: CreateHabitViewModel

    private var isEditMode=false
    private var currentHabitId: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_habit, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CreateHabitViewModel::class.java]

        val iconOptions = listOf("Fitness", "Water", "Study", "Meditation")
        val iconAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            iconOptions
        )
        binding.spinnerIcon.setAdapter(iconAdapter)

        currentHabitId = arguments?.getLong("HABIT_ID") ?: 0L

        if (currentHabitId > 0L) {
            isEditMode = true
            viewModel.fetchHabit(currentHabitId)
        } else {
            isEditMode = false
            binding.habit = HabitModel(
                id = System.currentTimeMillis(),
                habitName = "",
                shortDescription = "",
                goal = 0,
                unit = "",
                icon = iconOptions.first()
            )
            binding.spinnerIcon.setText(iconOptions.first(), false)
        }

        viewModel.habitLD.observe(viewLifecycleOwner) { habit ->
            binding.habit = habit
            binding.etGoal.setText(habit.goal.toString())
            binding.spinnerIcon.setText(habit.icon, false)
        }

        binding.btnCreateHabit.setOnClickListener {

            val habitData = binding.habit

            if (habitData != null) {
                habitData.goal = binding.etGoal.text.toString().toIntOrNull() ?: 0
                habitData.icon = binding.spinnerIcon.text.toString()

                if (habitData.progress > habitData.goal) {
                    habitData.progress = habitData.goal
                }

                habitData.isCompleted = habitData.progress >= habitData.goal

                if (isEditMode) {
                    viewModel.updateHabit(habitData)
                    Toast.makeText(requireContext(), "Habit updated!", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.saveHabit(habitData)
                    Toast.makeText(requireContext(), "Habit saved!", Toast.LENGTH_LONG).show()
                }
            }
            findNavController().navigateUp()
        }
    }
}