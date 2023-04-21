package com.example.fruitshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.fruitshop.databinding.FragmentInboxBinding

class InboxFragment : Fragment() {

    private lateinit var binding: FragmentInboxBinding
    private val fruitShopViewModel: FruitShopViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_inbox, container, false)

        viewDeleteMessage()

        fruitShopViewModel.messageChat.observe(viewLifecycleOwner, Observer {
            binding.messageInbox.text = fruitShopViewModel.getMessageChat()
            viewDeleteMessage()
        })

        binding.deleteMessage.setOnClickListener{
            fruitShopViewModel.deleteMessage()
            viewDeleteMessage()
        }

        return binding.root
    }

    fun viewDeleteMessage(){
        if(binding.messageInbox.text.toString().trim().isEmpty()){
            binding.deleteMessage.visibility = View.GONE
        }else{
            binding.deleteMessage.visibility = View.VISIBLE
        }
    }
}