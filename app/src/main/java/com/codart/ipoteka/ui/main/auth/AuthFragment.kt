package com.codart.ipoteka.ui.main.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codart.ipoteka.R
import com.codart.ipoteka.data.entities.Token
import com.codart.ipoteka.utils.Resource
import com.codart.ipoteka.utils.Values
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()

    private lateinit var inputNumber: EditText
    private lateinit var inputPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inputNumber = requireView().findViewById(R.id.edit_auth_number)
        inputPassword = requireView().findViewById(R.id.edit_auth_password)
        logIn()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun logIn(){
        var token: Token
        requireView().findViewById<AppCompatButton>(R.id.btn_auth_log_in).setOnClickListener {
            //findNavController().navigate(R.id.action_authFragment_to_profileFragment)
            viewModel.getNewToken().observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        Log.d("Auth", "Token: ${it.data!!.access_token}")
                        token = it.data
                        viewModel.login("Bearer " + it.data!!.access_token, inputNumber.text.toString(),inputPassword.text.toString()).observe(viewLifecycleOwner, {
                            when (it.status) {
                                Resource.Status.SUCCESS -> {
                                    if (it.data!!.success == 1) {
                                        Log.d("Auth", "Success, ${it.data!!.data!!.customer_id}")
                                        viewModel.setUserToken(token)
                                        findNavController().navigate(R.id.action_authFragment_to_profileFragment)
                                    } else {
                                        Log.d("Auth", "Error, ${it.data!!.error[0]}")
                                    }
                                }
                                Resource.Status.ERROR -> {
                                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                                    Log.d("Auth", "Error, ${it.message}")
                                    try {
                                        Log.d("Auth", "Success: ${it.data!!.success}")
                                        Log.d("Auth", "Error: ${it.data!!.error.toString()}")
                                    }
                                    catch (e:Exception)
                                    {
                                        Log.d("Auth", e.toString())
                                    }
                                }

                                Resource.Status.LOADING -> {
                                }
                            }
                        })

                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING -> {
                    }
                }
            })
        }
    }

}