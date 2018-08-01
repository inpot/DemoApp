package com.example.daniel.module.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView
import com.example.daniel.module.SevenActivity
import dagger.Component

interface SevenContract {

    @PerActivity
    @Component(modules = [SevenModule::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(activity: SevenActivity)
    }

    interface View : IView

    interface Repository : IRepository
}