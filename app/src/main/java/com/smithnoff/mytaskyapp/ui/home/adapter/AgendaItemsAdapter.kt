package com.smithnoff.mytaskyapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding

class AgendaItemsAdapter : RecyclerView.Adapter<AgendaItemsAdapter.AgendaItemsVH>() {

    private var taskList = mutableListOf<TaskyTask>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(tasks:List<TaskyTask>){
       taskList = tasks.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaItemsVH {
        val view = ItemCalendarTaskCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgendaItemsVH(view)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: AgendaItemsVH, position: Int) {
        holder.bind()
    }

   inner class AgendaItemsVH(private val itemViewCal: ItemCalendarTaskCardBinding) : RecyclerView.ViewHolder(itemViewCal.root) {

        fun bind() {
            itemViewCal.cardTitle.text = "Meeting"
        }
    }
}