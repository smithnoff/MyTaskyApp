package com.smithnoff.mytaskyapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyAgendaItem
import com.smithnoff.mytaskyapp.data.models.TaskyEvent
import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarEventCardBinding
import com.smithnoff.mytaskyapp.databinding.ItemCalendarReminderCardBinding
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding
import com.smithnoff.mytaskyapp.ui.home.adapter.viewholder.EventViewHolder
import com.smithnoff.mytaskyapp.ui.home.adapter.viewholder.ReminderViewHolder
import com.smithnoff.mytaskyapp.ui.home.adapter.viewholder.TaskViewHolder


class AgendaItemsAdapter(private val menuListener: (View) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var taskList = mutableListOf<TaskyAgendaItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(tasks: List<TaskyAgendaItem>) {
        taskList = tasks.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (TaskyTaskViewType.values()[viewType]) {
            TaskyTaskViewType.TASK -> {
                TaskViewHolder(
                    ItemCalendarTaskCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            TaskyTaskViewType.EVENT -> {
                EventViewHolder(
                    ItemCalendarEventCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            TaskyTaskViewType.REMINDER
            ->{
                ReminderViewHolder(
                    ItemCalendarReminderCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            TaskyTaskViewType.NIDDLE -> TODO()
        }
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(taskList[position]){
            is TaskyTask -> (holder as TaskViewHolder).bind((taskList[position] as TaskyTask),menuListener)
            is TaskyEvent -> (holder as EventViewHolder).bind((taskList[position] as TaskyEvent))
            is TaskyReminder -> (holder as ReminderViewHolder).bind((taskList[position] as TaskyReminder),menuListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (taskList[position]) {
            is TaskyTask -> TaskyTaskViewType.TASK.ordinal
            is TaskyEvent -> TaskyTaskViewType.EVENT.ordinal
            is TaskyReminder -> TaskyTaskViewType.REMINDER.ordinal
            else -> TaskyTaskViewType.NIDDLE.ordinal
        }
    }
}

enum class TaskyTaskViewType {
    TASK,
    EVENT,
    REMINDER,
    NIDDLE
}