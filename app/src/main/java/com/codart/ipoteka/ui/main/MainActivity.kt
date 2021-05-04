package com.codart.ipoteka.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.codart.ipoteka.R
import com.codart.ipoteka.data.repository.AppRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.fragment_main)

        if(intent.getBooleanExtra("isLogged",false))
        {
            navController.navigate(R.id.action_categoriesFragment_to_profileFragment)
            Log.d("Logged", "true")
        }



    }
}