package com.example.daniel.module.di

import android.support.v7.widget.RecyclerView
import app.base.di.modules.LayoutManagerModules
import app.base.di.scope.ListType
import com.example.daniel.module.ForthListAdapter
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.example.daniel.module.ForthVM
import com.example.daniel.module.model.ForthP

@Module(includes = [LayoutManagerModules::class])
class ForthModule(val view: ForthContract.View) {

    @Provides
    @PerActivity
    fun provideVM(repository: ForthP, @Named(ListType.VERTICAL) layoutManager: RecyclerView.LayoutManager) = ForthVM(repository, view, layoutManager, ForthListAdapter())

}