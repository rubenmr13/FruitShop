package com.example.fruitshop

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fruitshop.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val userViewModel: UserViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        var writeUser: Boolean = false
        var nameUser = userViewModel.getUser()
        var writePassword : Boolean = false


       userViewModel.user.observe(viewLifecycleOwner, Observer { newUser ->
           if(newUser !=""){
               binding.textUserWelcome.text = getString(R.string.text_user_welcome)+" "+ newUser.toString()
           }
           viewsUser()
       })

        viewsUser()

        binding.userEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // se llama antes de que el texto en el EditText cambie
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //se llama durante el cambio del texto en el EditText
                writeUser = true
                nameUser = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                //se llama después de que el texto en el EditText cambie

            }
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                writePassword = true
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })


        binding.signIn.setOnClickListener{
            if(writeUser && writePassword){
                findNavController().navigate(R.id.action_userFragment_self)
                userViewModel.addUser(nameUser)
            }
        }


        binding.signUp.setOnClickListener{
            if(writeUser && writePassword) {
                findNavController().navigate(R.id.action_userFragment_self)
                userViewModel.addUser(nameUser)
            }
        }

        binding.logOut.setOnClickListener {
            nameUser = ""
            binding.textUserWelcome.text = ""
            userViewModel.addUser(nameUser)

        }

        return binding.root
    }


    fun viewsUser(){
        if(binding.textUserWelcome.text.toString().trim().isEmpty()){
            binding.userEditText.visibility = View.VISIBLE
            binding.passwordEditText.visibility = View.VISIBLE
            binding.signIn.visibility = View.VISIBLE
            binding.signUp.visibility = View.VISIBLE
            binding.textUserWelcome.visibility = View.GONE
            binding.logOut.visibility = View.GONE

        }else{
            binding.userEditText.visibility = View.GONE
            binding.passwordEditText.visibility = View.GONE
            binding.signIn.visibility = View.GONE
            binding.signUp.visibility = View.GONE
            binding.textUserWelcome.visibility = View.VISIBLE
            binding.logOut.visibility = View.VISIBLE
        }
    }
}
