package dev.danilo.maders.feature.register.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.danilo.maders.R

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, RegisterActivity::class.java)
    }
}