package com.effectivemobile.courses.presentation.ui.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.effectivemobile.courses.R
import com.effectivemobile.courses.databinding.FragmentSignInBinding
import dev.androidbroadcast.vbpd.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        updateLoginButtonState()
    }

    private fun setupListeners() = with(binding) {
        etEmail.doAfterTextChanged { updateLoginButtonState() }
        etPassword.doAfterTextChanged { updateLoginButtonState() }

        btnLogin.setOnClickListener {
            if (btnLogin.isEnabled) {
                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
            }
        }

        btnVk.setOnClickListener {
            openUrl("https://vk.com/")
        }

        btnOk.setOnClickListener {
            openUrl("https://ok.ru/")
        }
    }

    private fun updateLoginButtonState() = with(binding) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        val isEmailValid = isValidEmail(email)
        val isPasswordValid = password.isNotEmpty()

        btnLogin.isEnabled = isEmailValid && isPasswordValid
        btnLogin.alpha = if (btnLogin.isEnabled) 1.0f else 0.5f
    }

    private fun isValidEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
        return email.matches(Regex(emailPattern))
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}