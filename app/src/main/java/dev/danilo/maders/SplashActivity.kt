package dev.danilo.maders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.danilo.maders.feature.login.fragment.LoginFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportFragmentManager.beginTransaction().replace(R.id.fl_root, LoginFragment()).commit()
    }
}