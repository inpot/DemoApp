package com.example.daniel.tab.di

import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.example.daniel.tab.ListVM
import com.example.daniel.tab.model.ListP

@Module
class ListModule(val view: ListContract.View) {

    @Provides
    @PerActivity
    fun provideVM(presenterP: ListP) = ListVM(presenterP, view)

}