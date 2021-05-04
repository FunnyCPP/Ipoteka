package com.codart.ipoteka.ui.main.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codart.ipoteka.R
import com.codart.ipoteka.ui.main.MainViewModel
import com.codart.ipoteka.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    lateinit var editName: EditText
    lateinit var editNumber: EditText

    val viewModel : CategoriesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editName = requireView().findViewById(R.id.edit_categories_name)
        editNumber = requireView().findViewById(R.id.edit_categories_number)
        setCategories()
        setProfileBtn()
        requireView().findViewById<AppCompatButton>(R.id.btn_categories_send).setOnClickListener {
            post()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    private fun setCategories(){
        requireView().findViewById<ConstraintLayout>(R.id.layout_categories_sailors).setOnClickListener {
            MainViewModel.calculatorId =1
            findNavController().navigate(R.id.action_categoriesFragment_to_calculatorFragment)
        }
        requireView().findViewById<ConstraintLayout>(R.id.layout_categories_home).setOnClickListener {
            MainViewModel.calculatorId =2
            findNavController().navigate(R.id.action_categoriesFragment_to_calculatorFragment)
        }
        requireView().findViewById<ConstraintLayout>(R.id.layout_categories_cars).setOnClickListener {
            MainViewModel.calculatorId =3
            findNavController().navigate(R.id.action_categoriesFragment_to_calculatorFragment)
        }
    }
    private fun setProfileBtn(){
        requireView().findViewById<ImageView>(R.id.img_categories_profile).setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_authFragment)
        }
    }
    private fun post(){
        viewModel.getToken().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("Auth", "Token: ${it.data!!.access_token}")
                    viewModel.postInput("Bearer " + it.data!!.access_token, editName.text.toString(),editNumber.text.toString()).observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.SUCCESS -> {
                                Toast.makeText(requireContext(), it.data!!.data, Toast.LENGTH_SHORT).show()
                            }
                            Resource.Status.ERROR -> {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

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