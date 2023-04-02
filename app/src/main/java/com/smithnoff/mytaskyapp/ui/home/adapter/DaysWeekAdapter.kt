package com.smithnoff.mytaskyapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.ItemCalendarDayBinding

class DaysWeekAdapter(val setDateListener:(Int)->Unit) : RecyclerView.Adapter<DaysWeekAdapter.DaysWeekVH>() {

    private var monthDays = mutableListOf<Pair<String,Int>>()
    private var selectedDay = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setDaysOfMonth(days:List<Pair<String,Int>>, currentDay:Int){
        monthDays = days.toMutableList()
        selectedDay = currentDay
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysWeekVH {
        val view = ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysWeekVH(view)
    }

    override fun getItemCount() = monthDays.size

    override fun onBindViewHolder(holder: DaysWeekVH, position: Int) {
        holder.bind(monthDays[position])
    }

   inner class DaysWeekVH(private val itemViewCal: ItemCalendarDayBinding) : RecyclerView.ViewHolder(itemViewCal.root) {

        fun bind(pair: Pair<String, Int>) {

              itemViewCal.txtDate.text = pair.second.toString()
              itemViewCal.txtWeekDay.text = pair.first[0].toString()
            if(selectedDay == pair.second) {
                itemViewCal.root.background = ContextCompat.getDrawable(
                    itemViewCal.root.context,
                    R.drawable.shape_circle_orange_background
                )
            }else{
                itemViewCal.root.background = null
            }
            itemViewCal.root.setOnClickListener {
                setDateListener.invoke(pair.second)
                selectedDay = pair.second
                itemViewCal.root.background = ContextCompat.getDrawable(
                    itemViewCal.root.context,
                    R.drawable.shape_circle_orange_background
                )
            }
        }
    }
}