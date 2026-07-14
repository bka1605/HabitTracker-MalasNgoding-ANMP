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

class DashboardFragment : Fragment() {

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

        binding.fabAddHabit.setOnClickListener {
            findNavController().navigate(R.id.actionHabitFragment)
        }

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        adapter = DashboardAdapter(
            object : DashboardListener {

                override fun onPlus(habit: HabitModel) {
                    viewModel.addProgress(habit)
                }

                override fun onMinus(habit: HabitModel) {
                    viewModel.minusProgress(habit)
                }

                override fun onHabitClick(habit: HabitModel) {
                    // Nunggu implementasi EditHabitFragment
                }
            }
        )

        viewModel.habitListLD.observe(viewLifecycleOwner) { habits ->
            adapter.setData(habits)
        }

        binding.recViewDashboard.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recViewDashboard.adapter = adapter

        viewModel.refresh()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}