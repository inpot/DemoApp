package app.base.di.modules

import android.content.Context
import app.base.R
import app.base.di.scope.PerApplication
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by daniel on 17-10-18.
 */

@Module
object HttpModules {
    @JvmStatic
    @Provides
    @PerApplication
    fun provideRetrofit(moshi: Moshi, context: Context): Retrofit {
        val baseUrl = context.getString(R.string.api_host)
        val builder = Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
        return builder.build()
    }

    @JvmStatic
    @Provides
    @PerApplication
    fun provideMoshi()  = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}