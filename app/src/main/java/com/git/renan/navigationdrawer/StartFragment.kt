package com.git.renan.navigationdrawer

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.git.renan.navigationdrawer.databinding.FragmentStartBinding

class StartFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,R.layout.fragment_start,container,false)
        binding.btContinue.setOnClickListener{ view:View ->
            view.findNavController().navigate(StartFragmentDirections.actionStartFragmentToGameFragment()) }
//            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_gameFragment))
//            Navigation.findNavController(view).navigate(R.id.action_startFragment_to_gameFragment) // modo 1
//            view.findNavController().navigate(R.id.action_startFragment_to_gameFragment) // modo 2

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}