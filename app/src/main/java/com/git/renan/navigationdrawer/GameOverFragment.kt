package com.git.renan.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.git.renan.navigationdrawer.databinding.FragmentResultBinding
import com.git.renan.navigationdrawer.databinding.FragmentResultOverBinding

class GameOverFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentResultOverBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_result_over,container,false)
        binding.buttonResult.setOnClickListener { view: View ->
            view.findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_game_title_fail_appbar)
        return binding.root
    }
}