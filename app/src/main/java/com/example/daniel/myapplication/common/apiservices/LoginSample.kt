
package com.example.daniel.myapplication.common.apiservices

import io.reactivex.Observable
import retrofit2.http.*
import kotlinx.coroutines.Deferred
import com.example.daniel.myapplication.com.example.daniel.module.model.QueryRes

/**
 * Created by daniel on 17-10-18.
 */
interface LoginSample {
    @FormUrlEncoded
    @POST("login")
    fun login(@FieldMap params: HashMap<String, String>): Observable<String>


    @GET("resolve")
    fun queryDns(@Query("name") domain: String, @Query("type") type: Int):Deferred<QueryRes>
}