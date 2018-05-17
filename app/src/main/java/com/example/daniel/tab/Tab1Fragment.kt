package com.example.daniel.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.base.BaseFragment
import com.example.daniel.myapplication.databinding.FragmentTab1Binding
import com.example.daniel.tab.di.DaggerTab1Contract_Comp
import com.example.daniel.tab.di.Tab1Contract
import com.example.daniel.tab.di.Tab1Module
import javax.inject.Inject
import com.example.daniel.myapplication.R

class Tab1Fragment : BaseFragment(), Tab1Contract.View {

    @Inject
    lateinit var vm: Tab1VM

    override fun buildComp() {
        DaggerTab1Contract_Comp.builder().activityComp(activityComp()).tab1Module(Tab1Module(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTab1Binding = bindViewModel(inflater, container, R.layout.fragment_tab1, vm)
        return binding.root
    }

}
