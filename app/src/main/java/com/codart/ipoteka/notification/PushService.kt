package com.codart.ipoteka.notification

import android.util.Log
import androidx.lifecycle.asFlow
import com.codart.ipoteka.data.repository.AppRepository
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PushService @Inject constructor(
    private val repository: AppRepository
): FirebaseMessagingService() {
    override fun onNewToken(newToken: String) {

        super.onNewToken(newToken)

            val firebaseToken = newToken
            Log.d("Firebase service", "New firebase token created"+firebaseToken)
            GlobalScope.launch(Dispatchers.IO) {
                repository.getUserToken().asFlow().collect {
                    Log.d("Firebase service", "Bearer token received "+it.access_token)
                    repository.sendFirebaseToken("Bearer "+it.access_token,firebaseToken!!).asFlow().collect {
                        Log.d("Firebase service", "Firebase token has sent "+it.data!!.data)
                    }
                }
            }
    }
}