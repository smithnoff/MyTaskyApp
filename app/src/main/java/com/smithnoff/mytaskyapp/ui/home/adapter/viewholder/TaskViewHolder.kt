package com.smithnoff.mytaskyapp.ui.home.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding
import com.smithnoff.mytaskyapp.utils.getCardDateHour

class TaskViewHolder(private val itemViewTask: ItemCalendarTaskCardBinding) : RecyclerView.ViewHolder(itemViewTask.root) {

    fun bind(task: TaskyTask, menuListener: (View) -> Unit){
        with(itemViewTask) {
            cardTitle.text = task.title
            cardDescription.text = task.description
            cardDate.text = getCardDateHour(task.time)
            cardOptions.setOnClickListener {
                menuListener.invoke(it)
            }
        }
    }
}