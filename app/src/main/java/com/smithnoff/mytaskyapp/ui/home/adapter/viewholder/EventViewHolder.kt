package com.smithnoff.mytaskyapp.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.data.models.TaskyEvent
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.ItemCalendarEventCardBinding
import com.smithnoff.mytaskyapp.databinding.ItemCalendarTaskCardBinding

class EventViewHolder(private val itemViewTask: ItemCalendarEventCardBinding) : RecyclerView.ViewHolder(itemViewTask.root) {

    fun bind(event:TaskyEvent){
        itemViewTask.cardTitle.text = event.title
        itemViewTask.cardDescription.text = event.description
    }
}