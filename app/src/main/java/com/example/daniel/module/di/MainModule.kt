package com.example.daniel.module.di


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import com.example.daniel.module.MainVM
import com.example.daniel.module.model.MainRep
import com.example.daniel.myapplication.common.APIServiceModule
import com.example.daniel.myapplication.common.DBModule

@Module(includes = [APIServiceModule::class,DBModule::class])
class MainModule(val view: MainContract.View) {

    @Provides
    @PerActivity
    fun provideVM(repository: MainRep): MainVM {
        var vm =
                when (view) {
                    is Fragment -> ViewModelProviders.of(view).get(MainVM::class.java)
                    is FragmentActivity -> ViewModelProviders.of(view).get(MainVM::class.java)
                    else -> MainVM()
                }
        vm.initialize(repository, view)
        return vm
    }

}
