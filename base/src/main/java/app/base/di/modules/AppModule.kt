package app.base.di.modules

import android.app.Application
import app.base.di.scope.PerApplication
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 17-11-27.
 */
@Module(includes = arrayOf(
        HttpModules::class,
        PreferenceModule::class))
class AppModule(val application: Application) {

    @PerApplication
    @Provides
    fun provideAppContext() = application.applicationContext

    @PerApplication
    @Provides
    fun provideApplication() = application

    @PerApplication
    @Provides
    fun provideResource() = application.resources

}