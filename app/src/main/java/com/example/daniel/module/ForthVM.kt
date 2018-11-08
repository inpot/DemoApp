package com.example.daniel.module

import androidx.recyclerview.widget.DiffUtil
import app.base.mvvm.vm.list.BaseListVM
import app.base.mvvm.vm.list.BaseListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.base.mvvm.repository.BaseObserver
import app.base.view.OnItemClick
import com.example.daniel.module.di.ForthContract
import com.example.daniel.tab.model.Book

class ForthVM(repository: ForthContract.Repository,
              view: ForthContract.View,
              layoutManager: RecyclerView.LayoutManager,
              adapter: BaseListAdapter<Book>
) : BaseListVM<ForthContract.Repository, ForthContract.View, Book>(repository, view, layoutManager, adapter), OnItemClick<Book> {


    init {
        adapter.onItemClick = this
    }

    override fun onLoadData(page: Int) {
        repository.loadContent(page, object : BaseObserver<List<Book>>(view) {
            override fun onNext(t: List<Book>) {
                bindResult(t)
            }

        })
    }

    override fun onItemClick(data: Book) {
        TODO()
    }

}

