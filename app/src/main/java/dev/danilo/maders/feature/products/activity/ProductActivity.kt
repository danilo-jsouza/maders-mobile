package dev.danilo.maders.feature.products.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity() {
    companion object {
        fun newInstance(context: Context) =
            Intent(context, ProductActivity::class.java)

    }
}