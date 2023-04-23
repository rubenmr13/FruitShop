package com.example.fruitshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fruitshop.databinding.FragmentMenuBinding

import androidx.navigation.Navigation

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        binding.fruitImage.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fruitShopFragment2)
        }

        binding.fishImage.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fishMarketFragment)
        }

        binding.meatImage.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_butcherShopFragment)
        }

        binding.sportImage.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_sportShopFragment)
        }

        return binding.root
    }

}

