package app.base.di.component

import app.base.BaseFragment
import app.base.di.scope.PerBaseFragment
import dagger.Component

/**
 * Created by daniel on 18-1-26.
 */
@PerBaseFragment
@Component(dependencies = [ActivityComp::class])
interface FragmentComp : ActivityComp{

    fun inject(baseFragment : BaseFragment)

}