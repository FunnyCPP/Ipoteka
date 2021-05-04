package com.codart.ipoteka.ui.main.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codart.ipoteka.R
import com.codart.ipoteka.data.entities.UserData
import com.codart.ipoteka.utils.Resource
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    lateinit var txtName: TextView
    lateinit var txtProcent: TextView
    lateinit var txtSum: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var content: Group
    lateinit var txtRequisites: TextView
    lateinit var txtExchange: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtName = requireView().findViewById(R.id.txt_profile_name)
        txtProcent = requireView().findViewById(R.id.txt_profile_procent)
        txtSum = requireView().findViewById(R.id.txt_profile_sum)
        recyclerView = requireView().findViewById(R.id.recycler_profile)
        content = requireView().findViewById(R.id.group_profile_content)
        progress = requireView().findViewById(R.id.progress_profile)
        txtRequisites = requireView().findViewById(R.id.txt_profile_requisit)
        txtExchange = requireView().findViewById(R.id.txt_profile_exchange)
        requireView().findViewById<ImageView>(R.id.img_profile_back).setOnClickListener {
            findNavController().navigateUp()
        }
        loadData()
        sendFirebaseToken()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun sendFirebaseToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task->
            if(!task.isSuccessful)
            {
                return@addOnCompleteListener
            }
            val firebaseToken = task.result
            Log.d("Firebase service", "New firebase token created: "+firebaseToken)
            viewModel.getUserToken().observe(viewLifecycleOwner, {
                Log.d("Firebase service", "Bearer token received "+it.access_token)
                viewModel.sendFirebaseToken("Bearer "+it.access_token,firebaseToken!!).observe(viewLifecycleOwner,{
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            Log.d("Firebase service", "Firebase token has sent ")
                        }
                        Resource.Status.ERROR -> {
                            Log.d("Firebase service", "Firebase token error "+it.message)

                        }

                        Resource.Status.LOADING -> {
                        }
                    }

                })
            })

        }
    }
    private fun loadData(){
        viewModel.getUserToken().observe(viewLifecycleOwner, {



                    viewModel.getUserData("Bearer " + it!!.access_token).observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.SUCCESS -> {
                                showData(it.data!!.data)
                            }
                            Resource.Status.ERROR -> {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                            }

                            Resource.Status.LOADING -> {
                            }
                        }
                    })

        })
    }
    private fun showData(data: UserData){
        try {
            txtName.text = data.customer_name
            txtProcent.text = data.total_procent
            txtSum.text = data.total_peny
            txtExchange.text = data.curs+" UAH/USD"
            try {
                txtRequisites.text = data.requisite[0].text
            }
            catch (e: Exception)
            {
                Log.e("Profile requisites",e.toString())
            }
            content.visibility = View.VISIBLE
            progress.visibility = View.GONE
            val adapter: ProfileAdapter = ProfileAdapter(requireContext())
            adapter.setItems(data.calendar_pay)
            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager

        }
        catch (e: Exception)
        {
            Log.e("Profile", e.toString())
        }
    }
}