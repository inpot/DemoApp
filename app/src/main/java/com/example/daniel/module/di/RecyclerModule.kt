package com.example.daniel.module.di

import android.support.v7.widget.RecyclerView
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
    fun provideVM(presenterP: RecyclerP, @Named(ListType.VERTICAL) layoutManager: RecyclerView.LayoutManager) = RecyclerVM(presenterP, view, layoutManager, RecyclerListAdapter())

}