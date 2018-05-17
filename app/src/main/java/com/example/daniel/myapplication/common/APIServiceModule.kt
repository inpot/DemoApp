package com.example.daniel.myapplication.common

import app.base.di.scope.PerActivity
import com.example.daniel.myapplication.common.apiservices.LoginSample
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by daniel on 17-10-18.
 */
@Module
object APIServiceModule {
    //sample login
    @JvmStatic
    @PerActivity
    @Provides
    fun provideLoginService(retrofit: Retrofit) = retrofit.create(LoginSample::class.java)
}