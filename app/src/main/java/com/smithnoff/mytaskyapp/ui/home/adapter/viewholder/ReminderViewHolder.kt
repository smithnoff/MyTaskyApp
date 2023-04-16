package com.smithnoff.mytaskyapp.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarReminderCardBinding
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding
import com.smithnoff.mytaskyapp.utils.getCardDateHour

class ReminderViewHolder(private val itemViewTask: ItemCalendarReminderCardBinding) : RecyclerView.ViewHolder(itemViewTask.root) {

    fun bind(reminder:TaskyReminder){
        itemViewTask.cardTitle.text = reminder.title
        itemViewTask.cardDescription.text = reminder.description
        itemViewTask.cardDate.text = getCardDateHour(reminder.time)
    }
}