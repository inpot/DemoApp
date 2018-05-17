package com.example.daniel.module.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import com.example.daniel.module.RecyclerActivity
import dagger.Component
import io.reactivex.Observer

interface RecyclerContract {
    @PerActivity
    @Component(modules = [RecyclerModule::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(activity: RecyclerActivity)
    }

    interface View : IView

    interface Presenter : IPresenter{
        fun loadData(page: Int, observer: Observer<List<String>>)
    }
}