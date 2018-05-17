package com.example.daniel.module.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.presenter.BaseObserver
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import com.example.daniel.module.ForthFragment
import com.example.daniel.tab.model.Book
import dagger.Component

interface ForthContract {

    @PerActivity
    @Component(modules = [ForthModule::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(fragment: ForthFragment)
    }

    interface View : IView

    interface Presenter : IPresenter{

        fun loadContent(page:Int, observer: BaseObserver<List<Book>>)

    }
}