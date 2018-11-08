package com.example.daniel.module.di

import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import com.example.daniel.module.MainVM
import com.example.daniel.module.model.MainRep

@Module
class MainModule(val view: MainContract.View) {

    @Provides
    @PerActivity
    fun provideVM(repository: MainRep):MainVM {
        var vm =
        when (view){
            is Fragment -> ViewModelProviders.of(view).get(MainVM::class.java)
            is FragmentActivity -> ViewModelProviders.of(view).get(MainVM::class.java)
            else -> MainVM(repository, view)
        }
        if(!vm.isInitialized()){
            vm.view = view
            vm.repository = repository
        }

        return vm
    }

}