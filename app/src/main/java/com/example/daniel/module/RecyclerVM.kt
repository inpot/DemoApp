package com.example.daniel.module

import android.content.Intent
import app.base.mvvm.vm.list.BaseListVM
import android.support.v7.widget.RecyclerView
import android.view.View
import app.base.mvvm.presenter.BaseObserver
import app.base.mvvm.vm.list.BaseListAdatper
import com.example.daniel.module.di.RecyclerContract
import com.example.daniel.tab.ThirdActivity

class RecyclerVM(presenter: RecyclerContract.Presenter,
                 view: RecyclerContract.View,
                 layoutManager: RecyclerView.LayoutManager,
                 adapter: BaseListAdatper<String>
) : BaseListVM<RecyclerContract.Presenter, RecyclerContract.View, String>(presenter, view, layoutManager, adapter) {

    override fun onLoadData(page: Int) {
        presenter.loadData(page, object : BaseObserver<List<String>> (view){
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

