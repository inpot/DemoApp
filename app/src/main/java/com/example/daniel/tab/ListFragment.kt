package com.example.daniel.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.base.BaseFragment
import com.example.daniel.myapplication.databinding.FragmentListBinding
import com.example.daniel.tab.di.DaggerListContract_Comp
import com.example.daniel.tab.di.ListContract
import com.example.daniel.tab.di.ListModule
import javax.inject.Inject
import com.example.daniel.myapplication.R

class ListFragment : BaseFragment(), ListContract.View {

    @Inject
    lateinit var vm: ListVM

    override fun buildComp() {
        DaggerListContract_Comp.builder().activityComp(activityComp()).listModule(ListModule(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentListBinding = bindViewModel(inflater, container, R.layout.fragment_list, vm)
        return binding.root
    }

}
