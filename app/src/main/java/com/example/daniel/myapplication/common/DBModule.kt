package com.example.daniel.myapplication.common

import androidx.room.Room
import app.base.di.scope.PerActivity
import com.example.daniel.myapplication.FlavorApplication
import com.example.daniel.myapplication.common.db.DBConfig
import com.example.daniel.myapplication.common.db.SampledDB
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 17-10-18.
 */
@Module
object DBModule {

    @PerActivity
    @JvmStatic
    @Provides
    fun provideUserDB() :SampledDB {
        return dbInstance
    }

    val dbInstance:SampledDB by lazy{
        Room.databaseBuilder(FlavorApplication.instance, SampledDB::class.java, DBConfig.DB_NAME).build()
    }


}