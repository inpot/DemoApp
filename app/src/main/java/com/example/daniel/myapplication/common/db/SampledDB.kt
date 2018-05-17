package com.example.daniel.myapplication.common.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by daniel on 17-10-19.
 */
@Database(entities = arrayOf(User::class), version = 1)
abstract class SampledDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}