package com.smithnoff.mytaskyapp.ui.home.adapter.viewholder

import android.view.MenuItem
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.data.models.TaskyAgendaItem
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding
import com.smithnoff.mytaskyapp.utils.getCardDateHour

class TaskViewHolder(private val itemViewTask: ItemCalendarTaskCardBinding) : RecyclerView.ViewHolder(itemViewTask.root),
    PopupMenu.OnMenuItemClickListener{

    private lateinit var listener : (TaskyAgendaItem, Int) -> Unit
    lateinit var selectedTask: TaskyTask
    fun bind(
        task: TaskyTask,
        optionListener:(TaskyAgendaItem, Int) -> Unit
    ){
        listener = optionListener
        selectedTask = task
        with(itemViewTask) {
            cardTitle.text = task.title
            cardDescription.text = task.description
            cardDate.text = getCardDateHour(task.time)
            cardOptions.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    setOnMenuItemClickListener(this@TaskViewHolder)
                    inflate(R.menu.menu_card_options)
                    show()
                }
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.open_item -> {
                listener.invoke(selectedTask,0)
            }
            R.id.edit_item -> {
                listener.invoke(selectedTask,1)
            }
            R.id.delete_item -> {
                listener.invoke(selectedTask,2)
            }
        }
        return true
    }
}