package com.smithnoff.mytaskyapp.ui.taskdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {

    private lateinit var viewModel: TaskDetailViewModel
    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_go_to_editInfoFragment)
        }
    }
}