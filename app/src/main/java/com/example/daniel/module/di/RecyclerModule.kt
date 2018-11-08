package com.example.daniel.module.di

import androidx.recyclerview.widget.RecyclerView
import app.base.di.modules.LayoutManagerModules
import app.base.di.scope.ListType
import com.example.daniel.module.RecyclerListAdapter
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.example.daniel.module.RecyclerVM
import com.example.daniel.module.model.RecyclerP
import com.example.daniel.myapplication.common.APIServiceModule
import com.example.daniel.myapplication.common.DBModule

@Module(includes = [LayoutManagerModules::class,APIServiceModule::class,DBModule::class])
class RecyclerModule(val view: RecyclerContract.View) {

    @Provides
    @PerActivity
    fun provideVM(repository: RecyclerP, @Named(ListType.VERTICAL) layoutManager: RecyclerView.LayoutManager) = RecyclerVM(repository, view, layoutManager, RecyclerListAdapter())

}