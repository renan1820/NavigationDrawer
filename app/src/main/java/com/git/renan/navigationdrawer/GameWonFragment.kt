package com.git.renan.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToStartFragment())
        }
        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        if (args != null) {
            Toast.makeText(context," NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestion}",Toast.LENGTH_LONG).show()
        }
        return binding.root
    }
}