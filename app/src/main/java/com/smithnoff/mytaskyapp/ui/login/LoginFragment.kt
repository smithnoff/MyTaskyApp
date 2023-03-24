package com.smithnoff.mytaskyapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btLogin.setOnClickListener {
         findNavController().navigate(R.id.action_loginFragment_to_agendaHomeFragment)
        }
        binding.btRegister.setOnClickListener {
         findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}