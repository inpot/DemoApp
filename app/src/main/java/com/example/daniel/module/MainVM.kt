package com.example.daniel.module

import android.content.Intent
import android.view.View
import app.base.mvvm.vm.BaseVM
import com.example.daniel.module.di.MainContract

class MainVM(repository: MainContract.Repository,
             view: MainContract.View
) : BaseVM<MainContract.Repository, MainContract.View>(repository, view){

    fun onBtnClick(view: View){
        val intent = Intent(view.context, RecyclerActivity::class.java)
        view.context.startActivity(intent)
    }
}
