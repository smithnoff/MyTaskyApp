package com.smithnoff.mytaskyapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.databinding.FragmentLoginBinding
import com.smithnoff.mytaskyapp.domain.validators.UserValidator
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.ValidationResult
import com.smithnoff.mytaskyapp.utils.changeDrawableRight
import com.smithnoff.mytaskyapp.utils.validateEmail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var validator: UserValidator

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
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_loginFragment_to_agendaHomeFragment)
                }
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loadingCircle.isVisible = it
        }
    }

    private fun initViews() {
        with(binding) {
            setEditTextListeners()
            btLogin.setOnClickListener {
                val userLoginRequest = UserLoginRequest(
                    etEmail.text.toString().trim(),
                    etPassword.text.toString().trim()
                )
                val validationResult = validator.loginUserTestValid(userLoginRequest)
                if (validationResult is ValidationResult.Failure) {
                    Toast.makeText(requireContext(), validationResult.errorMessage, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.doLogin(etEmail.text.toString(), etPassword.text.toString())
                }
            }

            btRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    private fun setEditTextListeners() {
        val drawableRight =
            AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_check_24)
        with(binding) {
            etEmail.doOnTextChanged { text, _, _, _ ->
                etEmail.changeDrawableRight(validateEmail(text.toString()), drawableRight)
            }
        }
    }
}