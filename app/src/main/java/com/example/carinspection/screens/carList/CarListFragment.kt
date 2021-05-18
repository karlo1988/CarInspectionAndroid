package com.example.carinspection.screens.carList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.carinspection.R
import com.example.carinspection.databinding.FragmentCarListBinding

class CarListFragment : Fragment() {
    private lateinit var binding: FragmentCarListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCarListBinding>(inflater, R.layout.fragment_car_list, container, false)
        binding.carList=this
        return binding.root
    }

}