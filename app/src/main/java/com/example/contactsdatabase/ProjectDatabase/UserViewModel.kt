package com.example.contactsdatabase.ProjectDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    var readAllData : LiveData<List<User>>?=null
    private var repository : UserRepository?=null

    init{
        val userDao = UserDatabase.getInstance(application).getuserDao()
        repository = UserRepository(userDao)
        readAllData = repository!!.readAllData
    }

    fun addUser(user : User){
        viewModelScope.launch(Dispatchers.IO){
            repository!!.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository!!.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.deleteUser(user)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository!!.deleteAll()
        }
    }
}