package com.example.daniel.module

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.base.mvvm.vm.BaseVM
import com.example.daniel.module.di.MainContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainVM() : BaseVM<MainContract.Repository, MainContract.View>() {


    var index =0;

    fun CoroutineClick(view: View){
//        loadData()
        viewModelScope.launch{
            val res = repository.queryDns("pl.goinbowl.com")
            Log.i("test",res.toString())
        }
    }
    fun MarqueeTextClick(view: View){

        marqueeTxt.value = when(index%4){
            0-> longTxt ;
        1->longTxt2;
            2-> short1;
            3->short2
            else -> longTxt2
        }
        index++
    }

    fun loadData(){
        viewModelScope.launch{

            Log.i("test", "1111 ${Thread.currentThread().name} : ${Thread.currentThread().id}")

            var res = getData();

            Log.i("test", "2222 ${Thread.currentThread().name} : ${Thread.currentThread().id}")
        }


    }

    val longTxt = "ANTLR Tool version 4.5.3 used for code generation does not match the current runtime version 4.7.1ANTLR Runtime version 4.5.3 used for parser compilation does not match the current runtime"
    val longTxt2 = "其野外种群数量仅500株左右。因数量稀少，濒危，五小叶槭被誉为四川植物界的大熊猫。作为世界上最具观赏价值的两种槭树之一"
    val short1 = "五小叶槭落户成都植物园"
    val short2 = "2021成都车展明日开幕\n"
    //val marqueeTxt  = ObservableField(longTxt)
    val marqueeTxt  = MutableLiveData(longTxt)


    suspend fun getData(): String{
        var res = withContext(Dispatchers.IO){
           Log.i("test", "3333 ${Thread.currentThread().name} : ${Thread.currentThread().id}")
            "result"
        }

        Log.i("test", "4444 ${Thread.currentThread().name} : ${Thread.currentThread().id}: $res")
        return res;



    }
}
