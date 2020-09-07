package com.git.renan.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.git.renan.navigationdrawer.databinding.FragmentResultBinding
import com.git.renan.navigationdrawer.databinding.FragmentResultWinBinding

class GameWonFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentResultWinBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_result_win,container,false)
        binding.buttonResult.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_startFragment)
        }
        return binding.root
    }
}