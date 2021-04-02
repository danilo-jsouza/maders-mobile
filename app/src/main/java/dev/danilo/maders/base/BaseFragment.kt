package dev.danilo.maders.base

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dev.danilo.maders.R

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private lateinit var binding: T
    private var toolbar: Toolbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = activity?.findViewById<Toolbar?>(R.id.toolbar)
        toolbar?.setTitle(getTitle())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenu(), menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    abstract fun getViewBinding(): T

    abstract fun getTitle(): Int

    abstract fun getMenu(): Int

}