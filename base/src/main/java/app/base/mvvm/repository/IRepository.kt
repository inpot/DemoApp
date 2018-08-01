package app.base.mvvm.repository

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner

/**
 * Created by daniel on 17-10-19.
 */
interface IRepository : LifecycleObserver {

    fun setLifecycleOwner(owner: LifecycleOwner)

}