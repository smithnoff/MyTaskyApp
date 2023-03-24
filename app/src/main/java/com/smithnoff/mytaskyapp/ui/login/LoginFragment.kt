package com.smithnoff.mytaskyapp.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentLoginBinding
import com.smithnoff.mytaskyapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
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
        initViews()
        initObservables()
    }

    private fun initObservables() {
        viewModel.loggedUserInfo.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                    Log.e("Login:", it.message ?: "Unknown error.")
                    setLoadingBarVisibility(false)
                }
                is NetworkResult.Loading -> setLoadingBarVisibility(it.isLoading)
                is NetworkResult.Success -> {
                    setLoadingBarVisibility(false)
                    findNavController().navigate(R.id.action_loginFragment_to_agendaHomeFragment)
                }
            }
        }
    }

    private fun setLoadingBarVisibility(loading: Boolean) {
        binding.loadingCircle.visibility = if (loading) VISIBLE else GONE
    }

    private fun initViews() {
        with(binding) {
            btRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btLogin.setOnClickListener {
                viewModel.doLogin(etEmail.text.toString(), etPassword.text.toString())
            }
        }
    }
}