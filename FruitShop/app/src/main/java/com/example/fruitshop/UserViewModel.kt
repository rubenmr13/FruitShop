package com.example.fruitshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _user

    fun addUser(name: String){
        _user.value = name
    }
    fun getUser(): String{
        return _user.value.toString()
    }

}