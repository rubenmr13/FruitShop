package com.example.fruitshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopViewModel: ViewModel() {
    ////// CHAT
    private val _messageChat = MutableLiveData<String>()
    val messageChat: LiveData<String>
        get() = _messageChat

    fun addMessageChat(text: String){
        val newText = _messageChat.value.toString() + text + "\n"
        _messageChat.value = newText
    }
    fun getMessageChat(): String{
        return _messageChat.value.toString()
    }

    fun initMessageChat(){
        if(_messageChat.value == null){
            _messageChat.value = ""
        }
    }

    ////// INBOX
    fun deleteMessage(){
        _messageChat.value = ""
    }
}