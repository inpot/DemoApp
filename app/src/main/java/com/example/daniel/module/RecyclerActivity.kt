package com.example.daniel.module

import android.os.Bundle
import app.base.BaseActivity
import com.example.daniel.myapplication.databinding.ActivityRecyclerBinding
import com.example.daniel.module.di.DaggerRecyclerContract_Comp
import com.example.daniel.module.di.RecyclerContract
import com.example.daniel.module.di.RecyclerModule
import javax.inject.Inject
import com.example.daniel.myapplication.R
import com.example.daniel.myapplication.common.DBModule

class RecyclerActivity : BaseActivity(), RecyclerContract.View {

    @Inject
    lateinit var vm: RecyclerVM

    override fun buildComp() {
        DaggerRecyclerContract_Comp.builder().activityComp(activityComp).dBModule(DBModule(this)).recyclerModule(RecyclerModule(this))
                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRecyclerBinding = bindViewModel(R.layout.activity_recycler, vm, false)
    }

}
