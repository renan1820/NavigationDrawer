package com.git.renan.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.git.renan.navigationdrawer.databinding.FragmentStartBinding

class StartFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,R.layout.fragment_start,container,false)
        binding.btContinue.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_gameFragment))

//            Navigation.findNavController(view).navigate(R.id.action_startFragment_to_gameFragment) // modo 1
//            view.findNavController().navigate(R.id.action_startFragment_to_gameFragment) // modo 2

        return binding.root
    }
}