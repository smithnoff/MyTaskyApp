package com.smithnoff.mytaskyapp.ui.editagenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smithnoff.mytaskyapp.databinding.FragmentEditInfoBinding

class EditInfoFragment : Fragment() {

    private lateinit var viewModel: EditInfoViewModel
    private var _binding: FragmentEditInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}