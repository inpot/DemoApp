package com.example.daniel.module

import app.base.mvvm.vm.list.BaseListVM
import app.base.mvvm.vm.list.BaseListAdatper
import android.support.v7.widget.RecyclerView
import app.base.mvvm.presenter.BaseObserver
import app.base.view.OnItemClick
import com.example.daniel.module.di.ForthContract
import com.example.daniel.tab.model.Book

class ForthVM(presenter: ForthContract.Presenter,
              view: ForthContract.View,
              layoutManager: RecyclerView.LayoutManager,
              adapter: BaseListAdatper<Book>
) : BaseListVM<ForthContract.Presenter, ForthContract.View, Book>(presenter, view, layoutManager, adapter), OnItemClick<Book> {


    init {
        adapter.onItemClick = this
    }

    override fun onLoadData(page: Int) {
        presenter.loadContent(page, object : BaseObserver<List<Book>>(view) {
            override fun onNext(t: List<Book>) {
                bindResult(t)
            }

        })
    }

    override fun onItemClick(data: Book) {
        TODO()
    }

}

