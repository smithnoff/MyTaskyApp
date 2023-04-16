package com.smithnoff.mytaskyapp.ui.reminder_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smithnoff.mytaskyapp.databinding.FragmentReminderDetailBinding

class ReminderDetailFragment : Fragment() {

    private var _binding: FragmentReminderDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderDetailBinding.inflate(layoutInflater)
        return binding.root
    }
}