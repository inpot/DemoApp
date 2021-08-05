package com.example.daniel.module

import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import app.base.mvvm.vm.BaseVM
import com.example.daniel.module.di.MainContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainVM() : BaseVM<MainContract.Repository, MainContract.View>() {



    fun TestClick(view: View){

        loadData()
        viewModelScope.launch{
            val res = repository.queryDns("pl.goinbowl.com")
            Log.i("test",res.toString())
        }
    }

    fun loadData(){
        viewModelScope.launch{

            Log.i("test", "1111 ${Thread.currentThread().name} : ${Thread.currentThread().id}")

            var res = getData();

            Log.i("test", "2222 ${Thread.currentThread().name} : ${Thread.currentThread().id}")
        }


    }


    suspend fun getData(): String{
        var res = withContext(Dispatchers.IO){
           Log.i("test", "3333 ${Thread.currentThread().name} : ${Thread.currentThread().id}")
            "result"
        }

        Log.i("test", "4444 ${Thread.currentThread().name} : ${Thread.currentThread().id}: $res")
        return res;



    }
}
