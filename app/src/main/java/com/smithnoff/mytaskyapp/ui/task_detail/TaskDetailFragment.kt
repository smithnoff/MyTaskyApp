package com.smithnoff.mytaskyapp.ui.task_detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.databinding.FragmentTaskDetailBinding
import com.smithnoff.mytaskyapp.domain.validators.TaskValidator
import com.smithnoff.mytaskyapp.ui.home.AgendaItemMenu
import com.smithnoff.mytaskyapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class TaskDetailFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance(Locale.ENGLISH)
    private val format = SimpleDateFormat("MMM dd yyyy", Locale.US)
    private val viewModel: TaskDetailViewModel by activityViewModels()
    private lateinit var reminderOptionSelected: ReminderOptions
    private var selectedTask: TaskyTask? = null
    private var option: Int = 0
    private val args: TaskDetailFragmentArgs by navArgs()

    @Inject
    lateinit var validator: TaskValidator

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        option = args.optionSelected
        selectedTask = args.agendaItem
        _binding = FragmentTaskDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btClose.setOnClickListener { findNavController().navigateUp() }
        if (option == AgendaItemMenu.OPEN.ordinal) {
            initViewsOnReadMode()
        } else {
            initViewsOnEditMode()
        }
        setReminderOptions()
        binding.btSaveTask.setOnClickListener {
            if (option == AgendaItemMenu.OPEN.ordinal) {
                configureEditButtons(true)
                option = AgendaItemMenu.EDIT.ordinal
            } else {
                if(option == AgendaItemMenu.EDIT.ordinal) {
                    configureEditButtons(false)
                    saveTask()
                }  else {
                    deleteTask()
                }
            }
        }
        initObservables()
    }

    private fun deleteTask() {

    }

    private fun initObservables() {
        with(viewModel) {
            taskTitle.observe(viewLifecycleOwner) {
                binding.cardTitle.text = it
            }

            taskDescription.observe(viewLifecycleOwner) {
                binding.cardDescription.text = it
            }

            createdTaskResult.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Error -> Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    is Resource.Success -> {
                        Toast.makeText(
                            requireContext(),
                            "Task created successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }

    private fun initViewsOnReadMode() {
        val format = SimpleDateFormat("dd MMMM yyyy", Locale.US)
        calendar.timeInMillis = selectedTask?.time ?: Date().time

        with(binding) {
            editScreenTitle.text =
                format.format(Date(selectedTask?.time ?: Calendar.getInstance().timeInMillis))
            setHourLabels()
        }
        configureEditButtons(false)
    }


    private fun initViewsOnEditMode() {
        with(binding) {
            setHourLabels()
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
            configureEditButtons(true)
        }
    }

    private fun saveTask() {
        val myTask = TaskyTask(
            id = selectedTask?.id?:UUID.randomUUID().toString(),
            title = binding.cardTitle.text.toString().trim(),
            description = binding.cardDescription.text.toString().trim(),
            time = calendar.timeInMillis,
            remindAt = calendar.timeInMillis - reminderOptionSelected.getMillis(),
            isDone = selectedTask?.isDone?:false
        )
        val validationResult = validator.createValidTask(myTask)
        if (validationResult is ValidationResult.Failure) {
            Toast.makeText(requireContext(), validationResult.errorMessage, Toast.LENGTH_SHORT)
                .show()
        } else {
            if(selectedTask == null)
            viewModel.createNewTask(myTask)
            else
                viewModel.updateTask(myTask)
        }
    }

    private fun setHourLabels() {
        with(binding) {
            cardTitle.text = selectedTask?.title
            cardDescription.text = selectedTask?.description
            fullDateSection.cardDestTitle.text = getString(R.string.txt_at)
            fullDateSection.cardDate.text = format.format(calendar.time)
            val hours = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)
            fullDateSection.cardHour.text =
                "${String.format("%02d", hours)}:${String.format("%02d", minutes)}"
        }
    }

    private fun showDateDialogPicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd =
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(Calendar.YEAR, selectedYear)
                calendar.set(Calendar.MONTH, selectedMonth)
                calendar.set(Calendar.DAY_OF_MONTH, selectedDay)
                binding.fullDateSection.cardDate.text = format.format(calendar.time)

            }, year, month, day)

        dpd.show()
    }

    private fun showTimeDialogPicker() {

        val mHour = calendar[Calendar.HOUR_OF_DAY]
        val mMinute = calendar[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                binding.fullDateSection.cardHour.text =
                    "${String.format("%02d", hourOfDay)}:${String.format("%02d", minute)}"
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
        reminderOptionSelected = selectedTask?.let {  getReminderTime(it.time,it.remindAt)}?: ReminderOptions.TenMinutes
        binding.btReminder.text = ReminderOptions.TenMinutes.getTitle(requireContext())
        binding.btReminder.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener(this@TaskDetailFragment)
                inflate(R.menu.menu_reminder_options)
                show()
            }
        }
    }

    private fun configureEditButtons(enabled: Boolean) {
        with(binding) {

            cardTitle.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                if (enabled) R.drawable.baseline_keyboard_arrow_right_24_black else 0,
                0
            )
            cardDescription.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                if (enabled) R.drawable.baseline_keyboard_arrow_right_24_black else 0,
                0
            )
            fullDateSection.cardDate.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                if (enabled) R.drawable.baseline_keyboard_arrow_right_24_black else 0,
                0
            )
            fullDateSection.cardHour.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                if (enabled) R.drawable.baseline_keyboard_arrow_right_24_black else 0,
                0
            )
            btSaveTask.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                if (!enabled) R.drawable.baseline_mode_edit_24 else 0,
                0
            )
            cardTitle.isEnabled = enabled
            cardDescription.isEnabled = enabled
            fullDateSection.cardDate.isEnabled = enabled
            fullDateSection.cardHour.isEnabled = enabled
            btDelete.isVisible = enabled
            if (!enabled) {
                btSaveTask.text = ""
            } else {
                btSaveTask.text = getString(R.string.txt_save)

            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        binding.btReminder.text = when (item?.itemId) {

            R.id.option_half_hour -> {
                reminderOptionSelected = ReminderOptions.HalfHour
                item.title
            }
            R.id.option_one_hour -> {
                reminderOptionSelected = ReminderOptions.OneHour
                item.title
            }
            R.id.option_six_hour -> {
                reminderOptionSelected = ReminderOptions.SixHour
                item.title
            }
            R.id.option_24_hour -> {
                reminderOptionSelected = ReminderOptions.OneDay
                item.title
            }
            else -> {
                reminderOptionSelected = ReminderOptions.TenMinutes
                item?.title
            }
        }
        return true
    }
}