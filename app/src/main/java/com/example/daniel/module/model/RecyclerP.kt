package com.example.daniel.module.model

import app.base.mvvm.presenter.BasePresenter
import javax.inject.Inject
import com.example.daniel.module.di.RecyclerContract
import com.example.daniel.myapplication.common.apiservices.LoginSample
import com.example.daniel.myapplication.common.db.SampledDB
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.TimeUnit

class RecyclerP @Inject constructor(val loginApi: LoginSample, val db: SampledDB) : BasePresenter(), RecyclerContract.Presenter {

        var page = 0
    override fun loadData(page: Int, observer: Observer<List<String>>) {
        this.page = page
        val data2 = if(page >2 ) listOf<String>() else List(25){"item ${it + page * 25}" }
       Observable.just(data2)
               .delay(1500, TimeUnit.MILLISECONDS)
               .compose(asyncRequest())
               .subscribe(observer)
    }
}