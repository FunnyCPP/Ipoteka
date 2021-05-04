package com.codart.ipoteka.ui.main.input

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
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.codart.ipoteka.R
import com.codart.ipoteka.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputFragment : Fragment() {

    val viewModel: InputViewModel by viewModels()

    lateinit var editSurname: EditText
    lateinit var editName: EditText
    lateinit var editParents: EditText
    lateinit var editNumber: EditText
    lateinit var editEmail: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editSurname = requireView().findViewById(R.id.edit_input_surname)
        editName = requireView().findViewById(R.id.edit_input_name)
        editParents = requireView().findViewById(R.id.edit_input_parents)
        editNumber = requireView().findViewById(R.id.edit_input_number)
        editEmail = requireView().findViewById(R.id.edit_input_email)
        setContinue()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun setContinue()
    {
        requireView().findViewById<AppCompatButton>(R.id.btn_input_continue).setOnClickListener {
        viewModel.getToken().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("Input", "Token: ${it.data!!.access_token}")
                    viewModel.postInput("Bearer " + it.data!!.access_token, editSurname.text.toString(),editName.text.toString(),editParents.text.toString(),editNumber.text.toString(),editEmail.text.toString(),"123").observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.SUCCESS -> {
                                Toast.makeText(requireContext(), it.data!!.data, Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_inputFragment_to_categoriesFragment)
                            }
                            Resource.Status.ERROR -> {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                                Log.e("Input error", it.message.toString())
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