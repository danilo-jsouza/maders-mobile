package dev.danilo.maders.feature.products.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import dev.danilo.maders.base.BaseFragment
import dev.danilo.maders.databinding.FragmentProductBinding
import dev.danilo.maders.feature.login.viewModel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding>() {
    private val viewModel: LoginViewModel by viewModel()

    override fun getViewBinding() = FragmentProductBinding.inflate(LayoutInflater.from(requireContext()))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
