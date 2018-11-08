package app.base.di.modules

import androidx.appcompat.app.AppCompatActivity
import app.base.di.scope.PerActivity
import app.base.di.scope.PerBaseActivity
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 17-11-27.
 */
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    @PerBaseActivity
    fun provideActivity() = activity

    @Provides
    @PerBaseActivity
    fun provideFragmentManager() = activity.supportFragmentManager

}