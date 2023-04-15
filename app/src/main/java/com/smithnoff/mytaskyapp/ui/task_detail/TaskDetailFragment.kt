package com.smithnoff.mytaskyapp.ui.task_detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentTaskDetailBinding
import com.smithnoff.mytaskyapp.utils.ReminderOptions
import com.smithnoff.mytaskyapp.utils.getTitle
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class TaskDetailFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance(Locale.ENGLISH)
    private val format = SimpleDateFormat("MMM dd yyyy", Locale.US)
    private val viewModel: TaskDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btClose.setOnClickListener { findNavController().navigateUp() }
        initViewsOnEditMode()
        initObservables()
    }

    private fun initObservables() {
        with(viewModel) {
            taskTitle.observe(viewLifecycleOwner) {
                binding.cardTitle.text = it
            }

            taskDescription.observe(viewLifecycleOwner) {
                binding.cardDescription.text = it
            }
        }
    }

    private fun initViewsOnEditMode() {
        with(binding) {
            fullDateSection.cardDestTitle.text = getString(R.string.txt_at)
            fullDateSection.cardDate.text = format.format(calendar.time)
            val hours = calendar.get(Calendar.HOUR_OF_DAY) + 1
            val minutes = calendar.get(Calendar.MINUTE)
            fullDateSection.cardHour.text = "${String.format("%02d", hours)}:${String.format("%02d", minutes)}"
            cardTitle.setOnClickListener {
                goToEditInfo(Bundle().apply
                {
                    putString(EditedField::class.java.name, EditedField.TITLE.name)
                })
            }
            cardDescription.setOnClickListener {
                goToEditInfo(Bundle().apply
                {
                    putString(EditedField::class.java.name, EditedField.DESCRIPTION.name)
                })
            }
            fullDateSection.cardHour.setOnClickListener {
                showTimeDialogPicker()
            }
            fullDateSection.cardDate.setOnClickListener {
                showDateDialogPicker()
            }

            setReminderOptions()
        }
    }

    private fun showDateDialogPicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(requireContext(),{ _, selectedYear, selectedMonth, selectedDay ->
             calendar.set(Calendar.YEAR,selectedYear)
             calendar.set(Calendar.MONTH,selectedMonth)
             calendar.set(Calendar.DAY_OF_MONTH,selectedDay)
            binding.fullDateSection.cardDate.text =format.format(calendar.time)

        }, year, month, day)

        dpd.show()
    }

    private fun showTimeDialogPicker() {

       val mHour = calendar[Calendar.HOUR_OF_DAY]
        val mMinute = calendar[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(requireContext(),
             { _, hourOfDay, minute ->
                binding.fullDateSection.cardHour.text = "${String.format("%02d",hourOfDay)}:${String.format("%02d",minute)}"
            },
            mHour,
            mMinute,
            true
        )
        timePickerDialog.show()
    }

    private fun goToEditInfo(bundle: Bundle) {
        findNavController().navigate(R.id.action_taskDetailFragment_to_taskEditInfoFragment, bundle)
    }

    private fun setReminderOptions() {
        binding.btReminder.text = ReminderOptions.TenMinutes.getTitle(requireContext())
        binding.btReminder.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener(this@TaskDetailFragment)
                inflate(R.menu.menu_reminder_options)
                show()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        binding.btReminder.text = when (item?.itemId) {

            R.id.option_half_hour -> {
                item.title
            }
            R.id.option_one_hour -> {
                item.title
            }
            R.id.option_six_hour -> {
                item.title
            }
            R.id.option_24_hour -> {
                item.title
            }
            else -> {
                item?.title
            }
        }
        return true
    }
}