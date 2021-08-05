package com.example.daniel.module.model

import android.content.SharedPreferences
import android.util.Log
import app.base.di.modules.PreferenceModule
import app.base.mvvm.repository.BaseRepository
import javax.inject.Inject
import com.example.daniel.module.di.MainContract
import com.example.daniel.myapplication.com.example.daniel.module.model.QueryRes
import com.example.daniel.myapplication.common.apiservices.LoginSample
import com.example.daniel.myapplication.common.db.SampledDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Named

class MainRep @Inject constructor(@Named(PreferenceModule.PREFERENCES_USER) val sharedPreferences: SharedPreferences, val loginApi:LoginSample,val db:SampledDB) : BaseRepository(), MainContract.Repository{

    override suspend fun queryDns(name: String): QueryRes {
        return withContext(Dispatchers.IO){
            val res =  loginApi.queryDns(name,1)
            Log.i("test", res.toString())
            res
        }
    }
}