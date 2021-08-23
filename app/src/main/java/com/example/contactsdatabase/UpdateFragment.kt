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
import androidx.navigation.NavArgs
import androidx.navigation.fragment.NavHostFragment
import com.example.contactsdatabase.ProjectDatabase.User
import com.example.contactsdatabase.ProjectDatabase.UserViewModel
import com.example.contactsdatabase.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mViewModel : UserViewModel
    private lateinit var args: UpdateFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_update,container,false)
        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        args = UpdateFragmentArgs.fromBundle(requireArguments())
        val currentUser = args.currentUser
        binding.editTextTextPersonName.setText(currentUser.firstName)
        binding.editTextTextPersonName2.setText(currentUser.lastName)
        binding.editTextTextPersonName3.setText(currentUser.age)

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
            val tempUser = User(args.currentUser.id,firstName,lastName,age)
            mViewModel.updateUser(tempUser)
            Toast.makeText(requireContext(),"Updated the user's data!!!", Toast.LENGTH_SHORT).show()
            NavHostFragment.findNavController(this).navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
        }
        else{
            Toast.makeText(requireContext(),"Please fill all the fields...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String,lastName: String,age: String) : Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }
}