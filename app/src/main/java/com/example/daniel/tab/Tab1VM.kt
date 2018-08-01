package com.example.daniel.tab

import android.content.Intent
import android.view.View
import app.base.mvvm.vm.BaseVM
import com.example.daniel.module.SevenActivity
import com.example.daniel.tab.di.Tab1Contract

class Tab1VM(repository: Tab1Contract.Repository,
             view: Tab1Contract.View
) : BaseVM<Tab1Contract.Repository, Tab1Contract.View>(repository, view){

    fun On7Click(view: View){
        val intent = Intent(view.context,SevenActivity::class.java)
        view.context.startActivity(intent)

    }

}
