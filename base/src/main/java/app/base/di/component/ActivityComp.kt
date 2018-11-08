package app.base.di.component

import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import app.base.di.modules.ActivityModule
import app.base.di.scope.PerBaseActivity
import dagger.Component

/**
 * Created by daniel on 17-11-28.
 */

@PerBaseActivity
@Component(modules = arrayOf(ActivityModule::class),dependencies = arrayOf(AppComp::class))
interface ActivityComp : AppComp {

    fun activity():AppCompatActivity

    fun fragmentManager(): FragmentManager

}