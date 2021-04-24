package dev.danilo.maders.feature.settings.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.danilo.maders.R
import dev.danilo.maders.feature.settings.fragment.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, SettingsFragment())
            .commit()
    }

}
