package com.example.daniel.tab.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import com.example.daniel.tab.Tab1Fragment
import dagger.Component

interface Tab1Contract {
    @PerActivity
    @Component(modules = [Tab1Module::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(fragment: Tab1Fragment)
    }

    interface View : IView

    interface Presenter : IPresenter
}