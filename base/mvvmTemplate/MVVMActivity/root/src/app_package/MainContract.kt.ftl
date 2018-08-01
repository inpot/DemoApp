package ${escapeKotlinIdentifiers(packageName)}.di

import app.base.di.component.ActivityComp
import app.base.di.scope.PerActivity
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView
import ${escapeKotlinIdentifiers(packageName)}.${activityClass}
import dagger.Component

interface ${moduleName?cap_first}Contract {

    @PerActivity
    @Component(modules = [${moduleName?cap_first}Module::class],
            dependencies = [ActivityComp::class])
    interface Comp : ActivityComp {
        fun inject(activity: ${activityClass})
    }

    interface View : IView

    interface Repository : IRepository
}