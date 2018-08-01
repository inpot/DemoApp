package com.example.daniel.module

import android.content.Intent
import app.base.mvvm.vm.list.BaseListVM
import android.support.v7.widget.RecyclerView
import android.view.View
import app.base.mvvm.repository.BaseObserver
import app.base.mvvm.vm.list.BaseListAdatper
import com.example.daniel.module.di.RecyclerContract
import com.example.daniel.tab.ThirdActivity

class RecyclerVM(repository: RecyclerContract.Repository,
                 view: RecyclerContract.View,
                 layoutManager: RecyclerView.LayoutManager,
                 adapter: BaseListAdatper<String>
) : BaseListVM<RecyclerContract.Repository, RecyclerContract.View, String>(repository, view, layoutManager, adapter) {

    override fun onLoadData(page: Int) {
        repository.loadData(page, object : BaseObserver<List<String>> (view){
            override fun onNext(t: List<String>) {
                bindResult(t)
            }
        })
    }

    fun btnClick(view: View){
        val intent = Intent(view.context, ThirdActivity::class.java)
        view.context.startActivity(intent)
    }
}

