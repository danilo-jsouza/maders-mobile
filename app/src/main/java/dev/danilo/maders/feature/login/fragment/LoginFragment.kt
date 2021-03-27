package dev.danilo.maders.feature.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.danilo.maders.databinding.FragmentLoginBinding
import dev.danilo.maders.feature.login.viewModel.LoginViewModel
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
        }

        viewModel.login.observe(this, Observer { isAuth ->
            if (isAuth) {
                Toast.makeText(requireContext(), "foi porra", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "fodeu", Toast.LENGTH_LONG).show()
            }

        })
    }
}
