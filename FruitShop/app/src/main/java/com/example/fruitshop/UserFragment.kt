package com.example.fruitshop

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.fruitshop.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        var writeUser: Boolean = false
        var writePassword : Boolean = false

        binding.user.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // se llama antes de que el texto en el EditText cambie
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //se llama durante el cambio del texto en el EditText
                writeUser = true
            }

            override fun afterTextChanged(s: Editable?) {
                //se llama después de que el texto en el EditText cambie

            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // se llama antes de que el texto en el EditText cambie
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //se llama durante el cambio del texto en el EditText
                writePassword = true
            }

            override fun afterTextChanged(s: Editable?) {
                //se llama después de que el texto en el EditText cambie

            }
        })


        binding.signIn.setOnClickListener{
            if(writeUser && writePassword){
                findNavController().navigate(R.id.action_userFragment_to_menuFragment)
            }
        }
        binding.singUp.setOnClickListener{
            if(writeUser && writePassword) {
                findNavController().navigate(R.id.action_userFragment_to_menuFragment)
            }
        }

        return binding.root
    }
}
