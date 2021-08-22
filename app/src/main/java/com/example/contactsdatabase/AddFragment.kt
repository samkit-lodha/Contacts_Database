package com.example.contactsdatabase

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.contactsdatabase.ProjectDatabase.User
import com.example.contactsdatabase.ProjectDatabase.UserViewModel
import com.example.contactsdatabase.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)
        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.button.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName : String = binding.editTextTextPersonName.text.toString()
        val lastName : String = binding.editTextTextPersonName2.text.toString()
        val age : String = binding.editTextTextPersonName3.text.toString()

        if(inputCheck(firstName,lastName,age)){
            val tempUser = User(0,firstName,lastName,age)
            mViewModel.addUser(tempUser)
            Toast.makeText(requireContext(),"Successfully Added!!!",Toast.LENGTH_SHORT).show()
            NavHostFragment.findNavController(this).navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please fill all the fields...",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String,lastName: String,age: String) : Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }
}