package com.example.contactsdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsdatabase.ProjectDatabase.User

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val idtv = itemView.findViewById<TextView>(R.id.indexTV)
        val fntv = itemView.findViewById<TextView>(R.id.firstNameTV)
        val lntv = itemView.findViewById<TextView>(R.id.lastNameTV)
        val agtv = itemView.findViewById<TextView>(R.id.ageTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_list_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.idtv.text = userList[position].id.toString()
        holder.fntv.text = userList[position].firstName
        holder.lntv.text = userList[position].lastName
        holder.agtv.text = userList[position].age

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(userList[position]))
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user : List<User>){
        userList = user
        notifyDataSetChanged()
    }
}