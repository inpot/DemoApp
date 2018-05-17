
package com.example.daniel.myapplication.common.apiservices

import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by daniel on 17-10-18.
 */
interface LoginSample {
    @FormUrlEncoded
    @POST("login")
    fun login(@FieldMap params: HashMap<String, String>): Observable<String>
}