package app.base.mvvm.vm

import androidx.lifecycle.ViewModel
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView

/**
 * Created by daniel on 17-10-19.
 */
abstract class BaseVM<Rep : IRepository, V: IView> () :ViewModel(){
    lateinit var repository:Rep
    lateinit var view:V
    constructor(repository:Rep,view:V) : this() {
        this.repository = repository
        this.view = view
    }

    open fun isInitialized() :Boolean{
        val result = ::view.isInitialized && ::repository.isInitialized
        return result

    }

}
