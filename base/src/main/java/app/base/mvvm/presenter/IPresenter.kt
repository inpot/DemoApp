package app.base.mvvm.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent

/**
 * Created by daniel on 17-10-19.
 */
interface IPresenter : LifecycleObserver {

    fun setLifecycleOwner(owner: LifecycleOwner)

}