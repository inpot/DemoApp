package com.example.daniel.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.base.BaseFragment
import com.example.daniel.myapplication.databinding.FragmentForthBinding
import com.example.daniel.module.di.DaggerForthContract_Comp
import com.example.daniel.module.di.ForthContract
import com.example.daniel.module.di.ForthModule
import javax.inject.Inject
import com.example.daniel.myapplication.R
import com.example.daniel.myapplication.databinding.FragmentForth2Binding

class ForthFragment : BaseFragment(), ForthContract.View {

    @Inject
    lateinit var vm: ForthVM

    override fun buildComp() {
        DaggerForthContract_Comp.builder().activityComp(activityComp()).forthModule(ForthModule(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentForth2Binding = bindViewModel(inflater, container, R.layout.fragment_forth2, vm)
        vm.onLoadData(0)
        return binding.root
    }

}
