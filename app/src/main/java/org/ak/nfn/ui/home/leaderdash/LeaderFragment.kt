package org.ak.nfn.ui.home.leaderdash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.ak.nfn.R

class LeaderFragment : Fragment() {

    companion object {
        fun newInstance() = LeaderFragment()
    }

    private lateinit var viewModel: LeaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.leader_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LeaderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}