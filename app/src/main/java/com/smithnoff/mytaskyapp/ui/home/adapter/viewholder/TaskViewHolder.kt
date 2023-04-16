package com.smithnoff.mytaskyapp.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding
import com.smithnoff.mytaskyapp.utils.getCardDateHour

class TaskViewHolder(private val itemViewTask: ItemCalendarTaskCardBinding) : RecyclerView.ViewHolder(itemViewTask.root) {

    fun bind(task:TaskyTask){
        itemViewTask.cardTitle.text = task.title
        itemViewTask.cardDescription.text = task.description
        itemViewTask.cardDate.text = getCardDateHour(task.time)
    }
}