package com.example.contactsdatabase

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsdatabase.ProjectDatabase.UserViewModel
import com.example.contactsdatabase.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mainViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)
        mainViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val adapter = MyAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        mainViewModel.readAllData!!.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_manu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteButton){
            deleteWholeList()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteWholeList() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mainViewModel.deleteAll()
            Toast.makeText(requireContext(),"Whole list cleared successfully!!!",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Clearing List")
        builder.setMessage("Are you sure you want to delete all data?")
        builder.create().show()
    }
}