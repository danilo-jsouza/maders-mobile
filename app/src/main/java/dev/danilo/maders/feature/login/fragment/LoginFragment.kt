package dev.danilo.maders.feature.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.danilo.maders.R
import dev.danilo.maders.databinding.FragmentLoginBinding
import dev.danilo.maders.extension.Result
import dev.danilo.maders.extension.isVisible
import dev.danilo.maders.feature.login.viewModel.LoginViewModel
import dev.danilo.maders.feature.products.activity.HomeActivity
import dev.danilo.maders.feature.register.activity.RegisterActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonLogin.setOnClickListener {
                viewModel.handleLogin(
                    edtUser.text.toString(),
                    edtPass.text.toString()
                )
            }
            buttonRegister.setOnClickListener { goToRegister() }
        }

        viewModel.login.observe(this, Observer { state ->
            val isLoading = state is Result.Loading
            binding.progressBar.isVisible(isLoading)
            if (isLoading) return@Observer

            when (state) {
                is Result.Success -> goToLogin()
                else -> handleError()
            }
        })
    }

    private fun handleError() {
        Toast.makeText(
            requireContext(),
            context?.getString(R.string.login_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun goToLogin() {
        startActivity(HomeActivity.newInstance(requireContext()))
        activity?.finish()
    }

    private fun goToRegister() {
        startActivity(RegisterActivity.newInstance(requireContext()))
    }
}
