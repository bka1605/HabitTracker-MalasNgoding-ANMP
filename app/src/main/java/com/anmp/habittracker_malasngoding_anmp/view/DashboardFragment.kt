package com.anmp.habittracker_malasngoding_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.anmp.habittracker_malasngoding_anmp.R
import com.anmp.habittracker_malasngoding_anmp.databinding.FragmentDashboardBinding
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitModel

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
    }
}