package org.ak.nfn.ui.home.heatsguideline

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.ak.nfn.R

class HeatsGuidelineFragment : Fragment() {

    companion object {
        fun newInstance() = HeatsGuidelineFragment()
    }

    private lateinit var viewModel: HeatsGuidelineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.heats_guideline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HeatsGuidelineViewModel::class.java)
        // TODO: Use the ViewModel
    }

}