package com.example.daniel.module.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView
import com.example.daniel.module.MainActivity
import dagger.Component

interface MainContract {
    @PerActivity
    @Component(modules = [MainModule::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(activity: MainActivity)
    }

    interface View : IView

    interface Repository : IRepository
}