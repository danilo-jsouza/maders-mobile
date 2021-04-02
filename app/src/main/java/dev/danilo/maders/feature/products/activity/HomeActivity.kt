package dev.danilo.maders.feature.products.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dev.danilo.maders.R
import dev.danilo.maders.databinding.ActivityHomeBinding
import dev.danilo.maders.feature.products.fragment.ProductFragment

class HomeActivity : AppCompatActivity() {
    companion object {
        fun newInstance(context: Context) =
            Intent(context, HomeActivity::class.java)
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        supportFragmentManager.beginTransaction().replace(R.id.fl_content, ProductFragment()).commit()
    }

}