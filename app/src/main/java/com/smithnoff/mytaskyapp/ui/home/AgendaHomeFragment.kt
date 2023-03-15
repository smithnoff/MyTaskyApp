package com.smithnoff.mytaskyapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentAgendaHomeBinding
import com.smithnoff.mytaskyapp.databinding.FragmentRegisterBinding

class AgendaHomeFragment : Fragment() {

    private lateinit var viewModel: AgendaHomeViewModel
    private var _binding: FragmentAgendaHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgendaHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}