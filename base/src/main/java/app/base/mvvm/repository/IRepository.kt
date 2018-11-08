package app.base.mvvm.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Created by daniel on 17-10-19.
 */
interface IRepository : LifecycleObserver {

    fun setLifecycleOwner(owner: LifecycleOwner)

}