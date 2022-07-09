package org.ak.nfn.ui.home.faq

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.ak.nfn.R

class FaqFragment : Fragment() {

    companion object {
        fun newInstance() = FaqFragment()
    }

    private lateinit var viewModel: FaqViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.faq_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FaqViewModel::class.java)
        // TODO: Use the ViewModel
    }

}