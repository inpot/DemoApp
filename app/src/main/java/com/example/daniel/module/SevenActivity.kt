package com.example.daniel.module

import android.os.Bundle
import app.base.BaseActivity
import com.example.daniel.myapplication.databinding.ActivitySevenBinding
import com.example.daniel.module.di.DaggerSevenContract_Comp
import com.example.daniel.module.di.SevenContract
import com.example.daniel.module.di.SevenModule
import javax.inject.Inject
import com.example.daniel.myapplication.R

class SevenActivity : BaseActivity(), SevenContract.View {

    @Inject
    lateinit var vm: SevenVM

    override fun buildComp() {
        DaggerSevenContract_Comp.builder().activityComp(activityComp).sevenModule(SevenModule(this))
                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySevenBinding = bindViewModel(R.layout.activity_seven, vm, true)
    }

}
