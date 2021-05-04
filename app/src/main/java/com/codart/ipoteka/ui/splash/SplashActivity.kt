package com.codart.ipoteka.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import com.codart.ipoteka.R
import com.codart.ipoteka.ui.main.MainActivity
import com.codart.ipoteka.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private  val  viewModel: MainViewModel by viewModels()
    private  var isLogged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.isLoggedIn().observe(this,{
            if(it != null)
            {
                isLogged =true
                Log.d("Logged", "true")
            }
        })
        Handler().postDelayed({

            startActivity(Intent(this,MainActivity::class.java).putExtra("isLogged",isLogged))
        },2000)
    }
}