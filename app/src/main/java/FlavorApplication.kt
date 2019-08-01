package com.example.daniel.myapplication

import app.base.BaseApplication

/**
 * Created by daniel on 17-12-25.
 */

class FlavorApplication: BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance:FlavorApplication
    }
}