package com.smithnoff.mytaskyapp.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyAgendaItem
import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.databinding.ItemCalendarReminderCardBinding
import com.smithnoff.mytaskyapp.utils.getCardDateHour

class ReminderViewHolder(private val itemViewTask: ItemCalendarReminderCardBinding) :
    RecyclerView.ViewHolder(itemViewTask.root) {

    fun bind(reminder: TaskyReminder, menuListener:  (TaskyAgendaItem, Int) -> Unit) {
        with(itemViewTask) {
            cardTitle.text = reminder.title
            cardDescription.text = reminder.description
            cardDate.text = getCardDateHour(reminder.time)
            cardOptions.setOnClickListener {
               // menuListener.invoke(it)
            }
        }
    }
}