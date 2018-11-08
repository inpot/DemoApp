package com.example.daniel.module

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import app.base.BaseActivity
import com.example.daniel.module.di.DaggerMainContract_Comp
import com.example.daniel.myapplication.databinding.ActivityMainBinding
import com.example.daniel.module.di.MainContract
import com.example.daniel.module.di.MainModule
import javax.inject.Inject
import com.example.daniel.myapplication.R

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var vm: MainVM

    override fun buildComp() {
        DaggerMainContract_Comp.builder().activityComp(activityComp).mainModule(MainModule(this))
                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.name.postValue("00000000")
        vm.name2.set("nam-----")
        val binding: ActivityMainBinding = bindViewModel(R.layout.activity_main, vm, false)
        binding.setLifecycleOwner(this)
    }

}
