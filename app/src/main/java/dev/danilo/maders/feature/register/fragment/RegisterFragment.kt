package dev.danilo.maders.feature.register.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import dev.danilo.maders.R
import dev.danilo.maders.databinding.FragmentRegisterBinding
import dev.danilo.maders.extension.Result
import dev.danilo.maders.feature.products.activity.HomeActivity
import dev.danilo.maders.feature.register.viewmodel.RegisterViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModel()

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener { sendRegister() }
        viewModel.register.observe(this) { state ->
            val isLoading = state is Result.Loading
            binding.progressBar.isVisible = isLoading
            if(isLoading) return@observe
            when (state) {
                is Result.Error -> handleError()
                else -> goToLogin()
            }
        }
    }

    private fun handleError() {
        Toast.makeText(
            requireContext(),
            context?.getString(R.string.register_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun goToLogin() {
        startActivity(HomeActivity.newInstance(requireContext()))
        activity?.finish()
    }

    private fun sendRegister() {
        binding.apply {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()
            val user = edtUser.text.toString()
            viewModel.fetchRegister(user, password, email)
        }
    }
}