package com.example.contactsdatabase

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_manu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteButton){
            deleteTheUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTheUser() {
        var builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"User successfully deleted!!!",Toast.LENGTH_SHORT).show()
            NavHostFragment.findNavController(this).navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
        }
        builder.setNegativeButton("No"){_,_->}

        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}