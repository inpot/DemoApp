package com.example.daniel.module.di

import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.example.daniel.module.SevenVM
import com.example.daniel.module.model.SevenRep

@Module
class SevenModule(val view: SevenContract.View) {

    @Provides
    @PerActivity
    fun provideVM(repository: SevenRep) = SevenVM(repository, view)

}