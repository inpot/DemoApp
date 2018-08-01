package com.example.daniel.tab.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView
import com.example.daniel.tab.ListFragment
import dagger.Component

interface ListContract {
    @PerActivity
    @Component(modules = [ListModule::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(fragment: ListFragment)
    }

    interface View : IView

    interface Repository : IRepository
}