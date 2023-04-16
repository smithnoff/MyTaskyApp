package com.smithnoff.mytaskyapp.ui.reminder_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.databinding.FragmentEditInfoBinding
import com.smithnoff.mytaskyapp.ui.task_detail.EditedField
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReminderEditInfoFragment : Fragment() {

    private var _binding: FragmentEditInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReminderDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         val fieldToEdit = arguments?.getString(EditedField::class.java.name)
       binding.btClose.setOnClickListener { findNavController().navigateUp() }

        when(fieldToEdit){
            EditedField.TITLE.name -> {
                configureTitleEditText()
            }
            EditedField.DESCRIPTION.name -> {
                configureDescriptionEditText()
            }
            else ->{}
        }
        with(binding){
            btSave.setOnClickListener {
                if (fieldToEdit == EditedField.TITLE.name )
                viewModel.setReminderTitle(fieldInfo.text.toString().replaceFirstChar { it.uppercaseChar() })
                else
                    viewModel.setReminderDescription(fieldInfo.text.toString().replaceFirstChar { it.uppercaseChar() })

                fieldInfo.setText( "")
                findNavController().navigateUp()
            }
            btClose.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun configureDescriptionEditText() {
        with(binding){
            editScreenTitle.text = "Edit Description"
            fieldInfo.setText(viewModel.getDescription())
            fieldInfo.textSize = 16F
            fieldInfo.isSingleLine = false
            btSave.setOnClickListener {
                viewModel.setReminderDescription(fieldInfo.text.toString())
                findNavController().navigateUp()
            }
        }

    }

    private fun configureTitleEditText() {
        with(binding){
            editScreenTitle.text = "Edit Title"
            fieldInfo.textSize = 26F
            fieldInfo.isSingleLine = true
            fieldInfo.setText(viewModel.getTitle())
            btSave.setOnClickListener {
            viewModel.setReminderTitle(fieldInfo.text.toString())
                findNavController().navigateUp()
            }
        }
    }
}