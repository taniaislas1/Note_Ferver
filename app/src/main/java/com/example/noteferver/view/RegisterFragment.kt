package com.example.noteferver.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteferver.R
import com.example.noteferver.databinding.RegisterFragmentBinding
import com.example.noteferver.viewModel.RegisterViewModel
import com.example.noteferver.utils.FragmentCommunicator

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var communicator: FragmentCommunicator
    var isValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        communicator = requireActivity() as OnboardingActivity
        setupView()
        return binding.root
    }

    private fun setupView() {
    binding.backButton.setOnClickListener {
        findNavController().navigate(R.id.action_registerFragment_to_signInFragment)

    }

        binding.nameTiet.addTextChangedListener {
            if (binding.nameTiet.text.toString().isEmpty()) {
                binding.nameTil.error = "Por favor introduce tu nombre"
                isValid = false
            } else {
                isValid = true
            }
        }
    binding.emailTiet.addTextChangedListener {
        if (binding.emailTiet.text.toString().isEmpty()) {
            binding.emailTil.error = "Por favor introduce un correo"
            isValid = false
        } else {
            isValid = true
        }
    }
    binding.passwordTiet.addTextChangedListener {
        if (binding.passwordTiet.text.toString().isEmpty()) {
            binding.passwordTil.error = "Por favor introduce una contraseña"
            isValid = false
        } else {
            isValid = true
        }
    }
        setupObservers()
}


    private fun setupObservers() {
    viewModel.loaderState.observe(viewLifecycleOwner) { loaderState ->
        communicator.showLoader(loaderState)
    }
        }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}