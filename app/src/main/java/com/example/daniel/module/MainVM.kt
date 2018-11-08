package com.example.daniel.module

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import app.base.mvvm.vm.BaseVM
import com.example.daniel.module.di.MainContract

class MainVM( ) : BaseVM< MainContract.Repository,  MainContract.View>(){

    val name = MutableLiveData<String>()
    val name2 = ObservableField<String>()
    constructor(repository: MainContract.Repository, view: MainContract.View):this(){
        this.repository = repository
        this.view = view
    }

    fun onBtnClick(view: View){
/*        val intent = Intent(view.context, RecyclerActivity::class.java)
        view.context.startActivity(intent)*/
        name.postValue("click here")
        name2.set("click2")
    }
}
