package com.anmp.habittracker_malasngoding_anmp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anmp.habittracker_malasngoding_anmp.R
import com.anmp.habittracker_malasngoding_anmp.databinding.FragmentDashboardBinding
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.viewmodel.DashboardViewModel

class DashboardFragment : Fragment(), HabitItemListener {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private lateinit var adapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        adapter = DashboardAdapter(arrayListOf(), this)

        viewModel.habitListLD.observe(viewLifecycleOwner) { habits ->
            adapter.setData(habits)
        }

        binding.recViewDashboard.layoutManager = LinearLayoutManager(requireContext())
        binding.recViewDashboard.adapter = adapter

        binding.fabAddHabit.setOnClickListener {
            findNavController().navigate(R.id.actionHabitFragment)
        }

        viewModel.refresh()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    override fun onPlusClick(v: View, habit: HabitModel) {
        viewModel.addProgress(habit)
    }

    override fun onMinusClick(v: View, habit: HabitModel) {
        viewModel.minusProgress(habit)
    }

    override fun onHabitClick(habit: HabitModel) {
        val action = Bundle().apply {
            putLong("HABIT_ID", habit.id)
        }
        findNavController().navigate(R.id.actionHabitFragment, action)
    }
}