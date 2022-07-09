package org.ak.nfn.ui.home.transaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.heats_transactions_list_item.view.*
import org.ak.nfn.R
import org.ak.nfn.data.pojo.Transaction

class TransactionAdapter(val transactions: List<Transaction>): RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {
    class TransactionHolder(view:View): RecyclerView.ViewHolder(view) {
        var transactionUser: TextView = view.findViewById(R.id.transactionUserName)
        var transactionDate: TextView = view.findViewById(R.id.transactionDate)
        var transactionTime: TextView = view.findViewById(R.id.transactionTime)
        var transcationAmount: TextView = view.findViewById(R.id.transactionAmount)

        fun bind(transcation: Transaction){
            transactionUser.text = transcation.username
            transactionDate.text = transcation.date
            transactionTime.text = transcation.time
            transcationAmount.text = transcation.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        return TransactionHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.heats_transactions_list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}