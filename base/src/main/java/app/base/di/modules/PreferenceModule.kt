package app.base.di.modules

import android.content.Context
import android.content.SharedPreferences
import app.base.di.scope.PerApplication
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 17-12-23.
 */
@Module
class PreferenceModule(val context: Context) {
    @PerApplication
    @Provides
    fun providePreference(): SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

}