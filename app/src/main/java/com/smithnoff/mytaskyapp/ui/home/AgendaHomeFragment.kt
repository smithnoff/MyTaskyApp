package com.smithnoff.mytaskyapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentAgendaHomeBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_agendaHomeFragment_to_eventDetailFragment)
        }
    }
}