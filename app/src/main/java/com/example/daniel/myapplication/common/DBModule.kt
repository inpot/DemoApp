package com.example.daniel.myapplication.common

import android.arch.persistence.room.Room
import android.content.Context
import app.base.di.scope.PerActivity
import com.example.daniel.myapplication.common.db.DBConfig
import com.example.daniel.myapplication.common.db.SampledDB
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 17-10-18.
 */
@Module
class DBModule(val context: Context) {

    @PerActivity
    @Provides
    fun provideUserDB() = Room.databaseBuilder(context, SampledDB::class.java, DBConfig.DB_NAME).build()

}