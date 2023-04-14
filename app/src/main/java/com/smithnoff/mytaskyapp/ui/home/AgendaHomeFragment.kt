package com.smithnoff.mytaskyapp.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.data.models.*
import com.smithnoff.mytaskyapp.databinding.FragmentAgendaHomeBinding
import com.smithnoff.mytaskyapp.ui.home.adapter.AgendaItemsAdapter
import com.smithnoff.mytaskyapp.ui.home.adapter.DaysWeekAdapter
import com.smithnoff.mytaskyapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import com.smithnoff.mytaskyapp.utils.SessionManagerUtil
import javax.inject.Inject

@AndroidEntryPoint
class AgendaHomeFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val selectedDate = Calendar.getInstance(Locale.ENGLISH)
    private var selectedDay = selectedDate[Calendar.DAY_OF_MONTH]
    private var currentMonth = currentDate[Calendar.MONTH]
    private var selectedMonth = selectedDate[Calendar.MONTH]
    private var currentYear = currentDate[Calendar.YEAR]
    private var selectedYear = selectedDate[Calendar.YEAR]
    private val viewModel: AgendaHomeViewModel by viewModels()
    private var _binding: FragmentAgendaHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var daysAdapter: DaysWeekAdapter
    private lateinit var agendaItemsAdapter: AgendaItemsAdapter
    private val format = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    private val months: Array<String> = DateFormatSymbols(Locale.US).months
    private val weekDays: Array<String> = DateFormatSymbols(Locale.US).weekdays

    @Inject
    lateinit var sessionManager: SessionManagerUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgendaHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUserMenuInfo()
        setCurrentDate()
        initMonthSelector()
        initObservables()
    }

    private fun initObservables() {
        viewModel.agendaItemList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    println(it.message)
                }
                is Resource.Success -> {
                    val agendaItems = mutableListOf<TaskyAgendaItem>()
                    agendaItems.addAll(it.data?.tasks!!)
                    agendaItems.addAll(it.data.events)
                    agendaItems.addAll(it.data.reminders)
                    agendaItemsAdapter.setItems(agendaItems)
                    println(it.data.toString())
                }
            }
        }
    }

    private fun setCurrentDate() {
        binding.rvCalendarDays.layoutManager =
            LinearLayoutManager(requireContext(), HORIZONTAL, false)
        binding.rvCalendarTask.layoutManager =
            LinearLayoutManager(requireContext())
        daysAdapter = DaysWeekAdapter(::selectMonthDay)
        agendaItemsAdapter = AgendaItemsAdapter()
        binding.rvCalendarDays.adapter = daysAdapter
        binding.rvCalendarTask.adapter = agendaItemsAdapter
        updateDaysAdapter(currentMonth)
        viewModel.getAgendaItems(TimeZone.getDefault().id,currentDate.timeInMillis)
    }

    private fun initMonthSelector() {
        setFullDateText()
        binding.monthSelector.text = months[currentMonth]
        binding.monthSelector.setOnClickListener {
            showDateDialogPicker()
        }
    }

    private fun selectMonthDay(day: Int) {
        selectedDate.set(selectedYear, selectedMonth, day)
        setFullDateText()
        updateDaysAdapter(currentMonth)
        viewModel.getAgendaItems(timeZone = TimeZone.getDefault().id, selectedDate.timeInMillis)
        binding.rvCalendarDays.smoothScrollToPosition(day - 1)
    }

    private fun getDayName(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
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

    private fun showDateDialogPicker() {
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, yearOf, monthOfYear, dayOfMonth ->
                selectedDate.set(yearOf, monthOfYear, dayOfMonth)
                selectedDay = dayOfMonth
                selectedYear = yearOf
                selectedMonth = monthOfYear
                setFullDateText()
                if (currentDate.time != selectedDate.time) {
                    updateDaysAdapter(monthOfYear)
                }
                binding.rvCalendarDays.smoothScrollToPosition(if (dayOfMonth <= 7 ) 0 else dayOfMonth)
                selectMonthDay(selectedDay)
                setFullDateText()
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun updateDaysAdapter(month: Int) {
        val daysOfMonth =
            (1..selectedDate.getActualMaximum(Calendar.DAY_OF_MONTH)).toList().toTypedArray()
                .map { Pair(getDayName(it, month, currentYear), it) }
        daysAdapter.setDaysOfMonth(daysOfMonth, selectedDay)
    }

    private fun setFullDateText() {
        binding.selectedDate.text =
            if (selectedDate.time == currentDate.time)
                getString(R.string.txt_today)
            else
                format.format(selectedDate.time)
        binding.monthSelector.text = months[selectedMonth]
        binding.rvCalendarDays.smoothScrollToPosition(selectedDay )

    }

    private fun setUserMenuInfo() {
        binding.userDropdown.text = sessionManager.getSessionInfo().fullName.abbrevName()
        binding.userDropdown.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener(this@AgendaHomeFragment)
                inflate(R.menu.menu_logout)
                show()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logout_user -> {
                sessionManager.logoutUser()
                findNavController().navigate(R.id.exit_to_login)
            }
        }
        return true
    }
}


fun String.abbrevName(): String {

    return if (this.trim().contains(' ')) {
        "${this.split(' ')[0].first()}${this.split(' ').last().first()}"
    } else {
        this.take(2).uppercase()
    }
}