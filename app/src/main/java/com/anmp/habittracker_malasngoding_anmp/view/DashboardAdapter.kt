package com.anmp.habittracker_malasngoding_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anmp.habittracker_malasngoding_anmp.databinding.DashboardItemListBinding
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.R

class DashboardAdapter (
    private val listener: DashboardListener
) : RecyclerView.Adapter<DashboardAdapter.HabitVH>() {
    private val items = mutableListOf<HabitModel>()

    fun setData(data: List<HabitModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class HabitVH(val binding: DashboardItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitVH {
        val binding = DashboardItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HabitVH(binding)
    }

    override fun onBindViewHolder(holder: HabitVH, position: Int) {
        val habit = items[position]
        val b = holder.binding
        b.habit = habit
        b.listener = listener

        b.executePendingBindings()

        b.tvProgressValue.text = "${habit.progress} / ${habit.goal} ${habit.unit}"
        b.progressHabit.max = habit.goal
        b.progressHabit.progress = habit.progress
        when (habit.icon) {
            "Water" -> b.imageView2.setImageResource(R.drawable.water)
            "Fitness" -> b.imageView2.setImageResource(R.drawable.ic_fitness)
            "Study" -> b.imageView2.setImageResource(R.drawable.ic_book)
            "Meditation" -> b.imageView2.setImageResource(R.drawable.ic_meditasi)
            else -> b.imageView2.setImageResource(R.drawable.ic_launcher_foreground)
        }

        if (habit.isCompleted) {
            b.tvStatus.text = "Completed"
            b.tvStatus.setBackgroundResource(com.anmp.habittracker_malasngoding_anmp.R.drawable.ic_background_completed)
        } else {
            b.tvStatus.text = "In Progress"
            b.tvStatus.setBackgroundResource(com.anmp.habittracker_malasngoding_anmp.R.drawable.ic_background_in_progress)
        }
    }

    override fun getItemCount(): Int = items.size
}