package dev.danilo.maders.base

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.navigation.NavigationView
import dev.danilo.maders.R

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected lateinit var binding: T

    protected var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        toolbar = activity?.findViewById<Toolbar?>(R.id.toolbar)?.also { it ->
            it.setTitle(getTitle())
            getMenu()?.let { menu ->
                it.inflateMenu(menu)
            }
            it.setOnMenuItemClickListener { item ->
                onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    abstract fun getViewBinding(): T

    abstract fun getTitle(): Int

    abstract fun getMenu(): Int?

    fun openFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.add(R.id.fl_content, fragment)
            ?.commit()
    }
}