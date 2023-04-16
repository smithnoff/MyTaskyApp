package com.smithnoff.mytaskyapp.ui.reminder_detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.databinding.FragmentReminderDetailBinding
import com.smithnoff.mytaskyapp.domain.validators.ReminderValidator
import com.smithnoff.mytaskyapp.ui.task_detail.EditedField
import com.smithnoff.mytaskyapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
@AndroidEntryPoint
class ReminderDetailFragment : Fragment(),PopupMenu.OnMenuItemClickListener {

    private var _binding: FragmentReminderDetailBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance(Locale.ENGLISH)
    private val format = SimpleDateFormat("MMM dd yyyy", Locale.US)
    private val viewModel: ReminderDetailViewModel by activityViewModels()
    private lateinit var reminderOptionSelected : ReminderOptions

    @Inject
    lateinit var validator: ReminderValidator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btClose.setOnClickListener { findNavController().navigateUp() }
        initViewsOnEditMode()
        initObservables()
    }

    private fun initObservables() {
        with(viewModel) {
            reminderTitle.observe(viewLifecycleOwner) {
                binding.cardTitle.text = it
            }

            reminderDescription.observe(viewLifecycleOwner) {
                binding.cardDescription.text = it
            }

            createdReminderResult.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    is Resource.Success -> {
                        Toast.makeText(requireContext(), "Reminder created successfully", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                }
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
            btSaveTask.setOnClickListener {
                val createdReminder = TaskyReminder(
                    id = UUID.randomUUID().toString(),
                    title = cardTitle.text.toString().trim(),
                    description = cardDescription.text.toString().trim(),
                    time = calendar.timeInMillis,
                    remindAt = calendar.timeInMillis - reminderOptionSelected.getMillis(),
                )
                val validationResult = validator.createValidReminder(createdReminder)
                if (validationResult is ValidationResult.Failure) {
                    Toast.makeText(requireContext(), validationResult.errorMessage, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.createNewReminder(createdReminder)
                }
            }
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
        findNavController().navigate(R.id.action_reminderDetailFragment_to_reminderEditInfoFragment, bundle)
    }

    private fun setReminderOptions() {
        reminderOptionSelected = ReminderOptions.TenMinutes
        binding.btReminder.text = ReminderOptions.TenMinutes.getTitle(requireContext())
        binding.btReminder.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener(this@ReminderDetailFragment)
                inflate(R.menu.menu_reminder_options)
                show()
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