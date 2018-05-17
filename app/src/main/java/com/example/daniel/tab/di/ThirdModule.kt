package com.example.daniel.tab.di

import android.support.v4.app.FragmentManager
import com.example.daniel.tab.ThirdPagerAdapter
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.example.daniel.tab.ThirdVM
import com.example.daniel.tab.model.ThirdP

@Module
class ThirdModule(val view: ThirdContract.View) {

    @Provides
    @PerActivity
    fun provideVM(presenterP: ThirdP, fragmentManager: FragmentManager) = ThirdVM(presenterP, view, ThirdPagerAdapter(fragmentManager))

}