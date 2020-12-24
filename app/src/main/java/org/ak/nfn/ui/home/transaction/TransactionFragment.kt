package org.ak.nfn.ui.home.transaction

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.transaction_fragment.*
import org.ak.nfn.R
import org.ak.nfn.data.pojo.Transaction
import org.ak.nfn.databinding.FragmentSignUpOneBinding
import org.ak.nfn.databinding.TransactionFragmentBinding

class TransactionFragment : Fragment() {
    //  Picasso.with(view.context).load(photo.url).into(view.itemImage)
    companion object {
        fun newInstance() = TransactionFragment()
    }

    private lateinit var viewModel: TransactionViewModel

    private lateinit var transactionAdapter: TransactionAdapter
    var transactions = listOf(
            Transaction("Asmit Bhantana","2020-12-13", "12:56 PM", 1234.56),
            Transaction("Sudip Bhantana","2020-09-13", "09:56 PM", 234.56),
            Transaction("Sujan Adhikari","2020-10-11", "10:56 PM", 134.56),
            Transaction("Dipak Adhikari","2020-12-12", "11:56 PM", 123.56),
            Transaction("Mani Adhikari","2020-11-13", "08:56 PM", 34.56),
            Transaction("Ramesh Adhikari","2020-11-13", "08:56 PM", 34.56)
    )

    lateinit var binding: TransactionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var v = inflater.inflate(R.layout.transaction_fragment, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.transaction_fragment, container, false)

        transactionAdapter = TransactionAdapter(transactions)
        binding.transactionRecyclerview.adapter = transactionAdapter
        binding.transactionRecyclerview.layoutManager = LinearLayoutManager(parentFragment?.context)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}