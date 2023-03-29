package com.smithnoff.mytaskyapp.ui.register

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
import com.smithnoff.mytaskyapp.data.models.RegisterUserRequest
import com.smithnoff.mytaskyapp.databinding.FragmentRegisterBinding
import com.smithnoff.mytaskyapp.domain.validators.UserValidator
import com.smithnoff.mytaskyapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var validator: UserValidator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservables()
    }

    private fun initObservables() {
        viewModel.registerUser.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()

                is Resource.Success -> Toast.makeText(
                    requireContext(),
                    "Registrado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.loadingCircle.isVisible = it
        }
    }

    private fun initViews() {
        with(binding) {
            setEditTextListeners()


            btLogin.setOnClickListener {
                val userInfo = RegisterUserRequest(
                    etFullname.text.toString().trim(),
                    etEmail.text.toString().trim(),
                    etPassword.text.toString().trim()
                )
                val validationResult = validator.registerUserTestValid(
                    userInfo
                )
                if (validationResult is ValidationResult.Failure) {
                    Toast.makeText(
                        requireContext(),
                        validationResult.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.doRegisterUser(userInfo)
                }
            }
            btBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun setEditTextListeners() {
        val drawableRight =
            AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_check_24)
        with(binding) {
            etFullname.doOnTextChanged { text, _, _, _ ->
                etFullname.changeDrawableRight(validateLength(text.toString()), drawableRight)
            }

            etEmail.doOnTextChanged { text, _, _, _ ->
                etEmail.changeDrawableRight(validateEmail(text.toString()), drawableRight)
            }

            etPassword.doOnTextChanged { text, _, _, _ ->
                etPassword.changeBackgroundError(validatePassword(text.toString()), tilPassword)
            }
        }
    }
}