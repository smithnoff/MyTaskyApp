package com.smithnoff.mytaskyapp.ui.eventdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smithnoff.mytaskyapp.databinding.FragmentEventDetailBinding

class EventDetailFragment : Fragment() {

    private lateinit var viewModel: EventDetailViewModel
    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}