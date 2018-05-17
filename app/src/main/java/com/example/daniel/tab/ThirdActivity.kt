package com.example.daniel.tab

import android.os.Bundle
import app.base.BaseActivity
import com.example.daniel.myapplication.databinding.ActivityThirdBinding
import com.example.daniel.tab.di.DaggerThirdContract_Comp
import com.example.daniel.tab.di.ThirdContract
import com.example.daniel.tab.di.ThirdModule
import javax.inject.Inject
import com.example.daniel.myapplication.R

class ThirdActivity : BaseActivity(), ThirdContract.View {

    @Inject
    lateinit var vm: ThirdVM

    override fun buildComp() {
        DaggerThirdContract_Comp.builder().activityComp(activityComp).thirdModule(ThirdModule(this))
                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityThirdBinding = bindViewModel(R.layout.activity_third, vm, false)
    }

}
