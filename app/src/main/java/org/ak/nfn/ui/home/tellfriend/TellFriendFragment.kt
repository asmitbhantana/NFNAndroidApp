package org.ak.nfn.ui.home.tellfriend

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.ak.nfn.R

class TellFriendFragment : Fragment() {

    companion object {
        fun newInstance() = TellFriendFragment()
    }

    private lateinit var viewModel: TellFriendViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tell_friend_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TellFriendViewModel::class.java)
        // TODO: Use the ViewModel
    }

}