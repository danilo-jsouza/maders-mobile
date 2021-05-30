package dev.danilo.maders

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remote: RemoteMessage) {
        super.onMessageReceived(remote)

        if (remote.notification != null) {
            startActivity(Intent(this, SplashActivity::class.java))
        }
    }


}