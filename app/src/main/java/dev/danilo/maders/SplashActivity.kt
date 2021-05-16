package dev.danilo.maders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.danilo.maders.feature.login.fragment.LoginFragment
import dev.danilo.maders.feature.products.activity.HomeActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val token = sharedPreferences.getToken()
        if (token != null && token.isNotBlank()) {
            startActivity(HomeActivity.newInstance(this))
            finish()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fl_root, LoginFragment()).commit()
        }
    }
}