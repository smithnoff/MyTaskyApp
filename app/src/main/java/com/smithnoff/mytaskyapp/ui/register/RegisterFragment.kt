package com.smithnoff.mytaskyapp.ui.register

import android.content.res.Resources
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smithnoff.mytaskyapp.R
import com.smithnoff.mytaskyapp.databinding.FragmentRegisterBinding
import com.smithnoff.mytaskyapp.utils.*

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        with(binding) {
            setEditTextListeners()
            btLogin.setOnClickListener {
               // viewModel.doRegisterUser()
            }
            btBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun setEditTextListeners() {
        val drawableRight = AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_check_24)
        with(binding){
            etFullname.doOnTextChanged { text, _, _, _ ->
                etFullname.changeDrawableRight(validateLength(text.toString()),drawableRight)
            }

            etEmail.doOnTextChanged { text, _, _, _ ->
               etEmail.changeDrawableRight(validateEmail(text.toString()),drawableRight)
            }

            etPassword.doOnTextChanged { text, _, _, _ ->
               etPassword.changeBackgroundError(validatePassword(text.toString()),tilPassword)
            }
        }
    }
}