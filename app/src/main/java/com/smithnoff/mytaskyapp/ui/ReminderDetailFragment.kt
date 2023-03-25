package com.smithnoff.mytaskyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smithnoff.mytaskyapp.databinding.FragmentReminderDetailBinding

class ReminderDetailFragment : Fragment() {

    private lateinit var viewModel: ReminderDetailViewModel
    private var _binding: FragmentReminderDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderDetailBinding.inflate(inflater, container, false)
        return binding.root    }
}