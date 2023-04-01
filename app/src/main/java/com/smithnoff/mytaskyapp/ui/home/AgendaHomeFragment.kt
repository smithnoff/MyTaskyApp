package com.smithnoff.mytaskyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentAgendaHomeBinding
import com.smithnoff.mytaskyapp.ui.home.adapter.DaysWeekAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AgendaHomeFragment : Fragment() {

    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val selectedDate = Calendar.getInstance(Locale.ENGLISH)
    private var currentDay = currentDate[Calendar.DAY_OF_MONTH]
    private var selectedDay = selectedDate[Calendar.DAY_OF_MONTH]
    private var currentMonth = currentDate[Calendar.MONTH]
    private var selectedMonth = selectedDate[Calendar.MONTH]
    private var currentYear = currentDate[Calendar.YEAR]
    private var selectedYear = selectedDate[Calendar.YEAR]
    private val viewModel: AgendaHomeViewModel by viewModels()
    private var _binding: FragmentAgendaHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DaysWeekAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgendaHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setCurrentDate()
        initMonthSelector()
    }

    private fun setCurrentDate() {
        val maxDay: Int = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        binding.rvCalendarDays.layoutManager =
            LinearLayoutManager(requireContext(), HORIZONTAL, false)
        adapter = DaysWeekAdapter(::setFullDate)
        val daysOfMonth = (1..maxDay).toList().toTypedArray()
            .map { Pair(getDayName(it, currentMonth, currentYear), it) }
        adapter.setDaysOfMonth(daysOfMonth,currentDay)
        binding.rvCalendarDays.adapter = adapter
    }

    private fun initMonthSelector() {
        val months: Array<String> = DateFormatSymbols(Locale.US).months
        binding.monthSelector.text = months[currentMonth]
        binding.monthSelector.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.txt_select_month))
                .setItems(months) { _, month ->
                    binding.monthSelector.text = months[month]
                    setFullDate(month = month)
                }.show()
        }
    }

    private fun setFullDate(day: Int = -1, month: Int = -1, year: Int = -1) {
        if (day >= 0) {
            selectedDay = day
        }
        if (month >= 0) {
            selectedMonth = month
            val date = Calendar.getInstance()
            date.set(currentYear, month, 1)
            val maxDay = date.getActualMaximum(Calendar.DAY_OF_MONTH)
            if (selectedDay > maxDay)
                selectedDay = maxDay
        }
        if (year >= 0) {
            selectedYear = year
        }

        selectedDate.set(selectedYear, selectedMonth, selectedDay)
        val daysOfMonth =
            (1..selectedDate.getActualMaximum(Calendar.DAY_OF_MONTH)).toList().toTypedArray()
                .map { Pair(getDayName(it, currentMonth, currentYear), it) }
        adapter.setDaysOfMonth(daysOfMonth,selectedDay)
        val format = SimpleDateFormat("dd MMMM yyyy", Locale.US)
        binding.selectedDate.text =
            if (selectedDate.time == currentDate.time) getString(R.string.txt_today) else format.format(selectedDate.time)
    }

    private fun getDayName(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val weekDays: Array<String> = DateFormatSymbols(Locale.US).weekdays
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> weekDays[1]
            2 -> weekDays[2]
            3 -> weekDays[3]
            4 -> weekDays[4]
            5 -> weekDays[5]
            6 -> weekDays[6]
            7 -> weekDays[7]
            else -> weekDays[0]
        }
    }
}