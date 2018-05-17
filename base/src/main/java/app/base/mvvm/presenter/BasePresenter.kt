package app.base.mvvm.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by daniel on 17-10-19.
 */
abstract class BasePresenter : LifecycleObserver {
    private  val subject = PublishSubject.create<Lifecycle.Event>()
    lateinit var owner: Lifecycle

    fun setLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
        this.owner = owner.lifecycle
    }

    fun <T> asyncRequest() = ObservableTransformer<T, T> {
            upstream -> upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .takeUntil(subject)
    }



    fun <T> bindLifecycle(observable: Observable<T>): Observable<T> {
        return observable.takeUntil(subject)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        subject.onNext(Lifecycle.Event.ON_DESTROY)
        subject.onComplete()
        owner.removeObserver(this)
    }
}