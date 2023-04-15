package com.smithnoff.mytaskyapp.ui.task_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.databinding.FragmentEditInfoBinding
import dagger.hilt.android.AndroidEntryPoint

enum class EditedField {
    TITLE,
    DESCRIPTION
}

@AndroidEntryPoint
class TaskEditInfoFragment : Fragment() {

    private var _binding: FragmentEditInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         val fieldToEdit =arguments?.getString(EditedField::class.java.name)
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
                viewModel.setTaskTitle(fieldInfo.text.toString().replaceFirstChar { it.uppercaseChar() })
                else
                    viewModel.setTaskDescription(fieldInfo.text.toString().replaceFirstChar { it.uppercaseChar() })

                findNavController().navigateUp()
            }
            btClose.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun configureDescriptionEditText() {
        with(binding){
            fieldInfo.setText( viewModel.getTitleDescription())
            fieldInfo.textSize = 16F
            fieldInfo.isSingleLine = false
            fieldInfo.setText(viewModel.getTitle())
            btSave.setOnClickListener {
                viewModel.setTaskTitle(fieldInfo.text.toString())
                findNavController().navigateUp()
            }
        }

    }

    private fun configureTitleEditText() {
        with(binding){
            fieldInfo.setText( viewModel.getTitle())
            fieldInfo.textSize = 26F
            fieldInfo.isSingleLine = true
            fieldInfo.setText(viewModel.getTitle())
            btSave.setOnClickListener {
                viewModel.setTaskTitle(fieldInfo.text.toString())
                findNavController().navigateUp()
            }
        }
    }
}