package com.example.daniel.tab.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import com.example.daniel.tab.ThirdActivity
import dagger.Component

interface ThirdContract {
    @PerActivity
    @Component(modules = [ThirdModule::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(activity: ThirdActivity)
    }

    interface View : IView

    interface Presenter : IPresenter
}