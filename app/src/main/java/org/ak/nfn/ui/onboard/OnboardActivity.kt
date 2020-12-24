package org.ak.nfn.ui.onboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_onboard.*
import org.ak.nfn.R
import java.util.*

class OnboardActivity : AppCompatActivity() {

    private val items = listOf(
        OnboardItem("Agriculture", "Agriculture is the sustainable field of work.", R.drawable.ilu_agri_slider),
        OnboardItem("Health", "You can work on health products.", R.drawable.ilu_health_slider),
        OnboardItem("Sports", "You can work in the sports field.", R.drawable.ilu_sports_slider),
        OnboardItem("Tourism", "You can work in various tourism field like Trekking, Tour Guide and Hotels.", R.drawable.ilu_tourism_slider),
        OnboardItem("Tech", "You can work in various tech field like Repairs, Maintenance and Sells.", R.drawable.ilu_it_slider)
    )
    private val onboardViewPagerAdapter = OnboardViewPagerAdapter(items)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        onboardViewPager.adapter = onboardViewPagerAdapter

        setupIndicator()
        setUpSelectedIndicator(0)
        setupAutoScrollable()
        onboardViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setUpSelectedIndicator(position)
                }
            }
        )



    }

    private fun setupAutoScrollable(){
        val handler = Handler()
        var currentPage = 0
        val update = Runnable {
            currentPage ++
            //The second parameter ensures smooth scrolling
            onboardViewPager.setCurrentItem(currentPage%items.size, true)
        }

        Timer().schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, 3500, 3500)
    }

    private fun setupIndicator(){
        val indicators = arrayOfNulls<ImageView>(onboardViewPagerAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply{
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboard_pager_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
    }

    private fun setUpSelectedIndicator(selected:Int){
        val childCount = indicatorContainer.childCount
        for( i in 0 until childCount){
            val imageView = indicatorContainer[i] as ImageView
            if ( i== selected){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboard_pager_active
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboard_pager_inactive
                    )
                )
            }
        }
    }
}