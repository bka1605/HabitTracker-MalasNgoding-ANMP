package com.anmp.habittracker_malasngoding_anmp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anmp.habittracker_malasngoding_anmp.databinding.FragmentHabitBinding
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.viewmodel.CreateHabitViewModel

class HabitFragment : Fragment() {
    private var _binding: FragmentHabitBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateHabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHabitBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CreateHabitViewModel::class.java]

        binding.btnCreateHabit.setOnClickListener {

            val name = binding.etHabitName.text.toString()
            val desc = binding.etDescription.text.toString()
            val goal = binding.etGoal.text.toString().toIntOrNull() ?: 0
            val unit = binding.etUnit.text.toString()
            val icon = binding.spinnerIcon.text.toString()

            val habit = HabitModel(
                id = System.currentTimeMillis(),
                habitName = name,
                shortDescription = desc,
                goal = goal,
                unit = unit,
                icon = icon
            )

            viewModel.saveHabit(habit)

            Log.d("HABIT_DEBUG", "Habit berhasil disimpan:")
            Log.d("HABIT_DEBUG", habit.toString())

            Toast.makeText(
                requireContext(),
                "Habit saved!",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}