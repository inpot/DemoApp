package com.example.daniel.module.model

import app.base.mvvm.presenter.BaseObserver
import app.base.mvvm.presenter.BasePresenter
import com.example.daniel.module.di.ForthContract
import com.example.daniel.tab.model.Book
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForthP @Inject constructor() : BasePresenter(), ForthContract.Presenter {

    var page = 0
    override fun loadContent(page: Int, observer: BaseObserver<List<Book>>) {
        this.page = page
        val data2 = if (page > 2) listOf<Book>() else List(25) {
            val id = it + page * 25
            Book(id, "Name $id ", id+2)
        }
        Observable.just(data2)
                .delay(1500, TimeUnit.MILLISECONDS)
                .compose(asyncRequest())
                .subscribe(observer)
    }
}