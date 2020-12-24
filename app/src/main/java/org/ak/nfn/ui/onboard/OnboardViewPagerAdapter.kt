package org.ak.nfn.ui.onboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.ak.nfn.R

class OnboardViewPagerAdapter (private val onboardItems: List<OnboardItem>):
    RecyclerView.Adapter<OnboardViewPagerAdapter.OnboardSlideViewHolder>() {


    inner class OnboardSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.onboardTitle)
        private val desc = view.findViewById<TextView>(R.id.onboardDesc)
        private val img = view.findViewById<ImageView>(R.id.onboardImage)

        fun bind(onboardItem: OnboardItem){
            title.text = onboardItem.title
            desc.text = onboardItem.description
            img.setImageResource(onboardItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardSlideViewHolder {
        return OnboardSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.onboard_screen_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: OnboardSlideViewHolder, position: Int) {
        holder.bind(onboardItems[position])
    }

    override fun getItemCount(): Int {
        return onboardItems.size
    }
}