package com.example.daniel.module.di

import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.example.daniel.module.MainVM
import com.example.daniel.module.model.MainP

@Module
class MainModule(val view: MainContract.View) {

    @Provides
    @PerActivity
    fun provideVM(presenterP: MainP) = MainVM(presenterP, view)

}