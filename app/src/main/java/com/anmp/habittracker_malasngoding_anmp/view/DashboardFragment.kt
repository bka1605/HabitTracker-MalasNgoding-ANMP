package com.anmp.habittracker_malasngoding_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anmp.habittracker_malasngoding_anmp.R
import com.anmp.habittracker_malasngoding_anmp.databinding.FragmentDashboardBinding
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private lateinit var adapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddHabit.setOnClickListener {
            findNavController().navigate(R.id.actionHabitFragment)
        }

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        adapter = DashboardAdapter(
            onPlus = {
                viewModel.addProgress(it)
                loadHabits()
            },
            onMinus = {
                viewModel.minusProgress(it)
                loadHabits()
            }
        )

        binding.recViewDashboard.adapter = adapter
        binding.recViewDashboard.layoutManager = LinearLayoutManager(requireContext())

        loadHabits()
    }
    override fun onResume() {
        super.onResume()
        loadHabits()
    }

    private fun loadHabits() {
        val habits = viewModel.getHabits()
        adapter.setData(habits)
    }
}