package com.git.renan.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
            Toast.makeText(context," YEAAH !! ${args.numCorrect} / ${args.numQuestion}",Toast.LENGTH_LONG).show()
        }
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_game_title_success_appbar)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)
        if(null == getShareIntent().resolveActivity(activity!!.packageManager)){
            //hide menu
            menu?.findItem(R.id.menu_share)?.setVisible(false)
        }
    }

    private fun getShareIntent() : Intent{
        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        return  ShareCompat.IntentBuilder.from(activity!!)
            .setText("Comprovante de Vitória ${args?.numCorrect} : ${args?.numQuestion}" ).setType("text/plain").intent

    }
    private fun shareSuccss(){
        startActivity(getShareIntent())
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_share -> shareSuccss()
        }
        return super.onOptionsItemSelected(item)
    }
}