package com.example.daniel.module.model

import android.content.SharedPreferences
import app.base.di.modules.PreferenceModule
import app.base.mvvm.repository.BaseRepository
import javax.inject.Inject
import com.example.daniel.module.di.MainContract
import com.example.daniel.myapplication.common.apiservices.LoginSample
import com.example.daniel.myapplication.common.db.SampledDB
import javax.inject.Named

class MainRep @Inject constructor(@Named(PreferenceModule.PREFERENCES_USER) sharedPreferences: SharedPreferences, loginApi:LoginSample,db:SampledDB) : BaseRepository(), MainContract.Repository