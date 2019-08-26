package com.example.daniel.module

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.base.BaseActivity
import com.example.daniel.myapplication.databinding.ActivityMainBinding
import com.example.daniel.module.di.DaggerMainContract_Comp
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
        val binding: ActivityMainBinding = bindViewModel(R.layout.activity_main, vm,false)
    }

    companion object {

        fun actionStart(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }

    }
}
