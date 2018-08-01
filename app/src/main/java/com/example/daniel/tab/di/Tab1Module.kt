package com.example.daniel.tab.di

import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import com.example.daniel.tab.Tab1VM
import com.example.daniel.tab.model.Tab1P

@Module
class Tab1Module(val view: Tab1Contract.View) {

    @Provides
    @PerActivity
    fun provideVM(repository: Tab1P) = Tab1VM(repository, view)

}