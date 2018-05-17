package com.example.daniel.tab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.daniel.module.ForthFragment

class ThirdPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val titles = listOf("Basic","list","Basic")
    override fun getItem(position: Int): Fragment {
        return when(position){
                 0 -> Tab1Fragment()
                 1 -> ForthFragment()
                 2 -> ListFragment()
                 else -> Tab1Fragment()
             }
    }

    override fun getCount(): Int =  titles.size

    override fun getPageTitle(position: Int): CharSequence?  =  titles[position]

}