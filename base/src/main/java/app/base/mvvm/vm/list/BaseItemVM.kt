package app.base.mvvm.vm.list

import androidx.databinding.BaseObservable
import android.view.View
import app.base.view.OnItemClick

/**
 * Created by daniel on 18-1-15.
 */
abstract class BaseItemVM<T>(val data: T, private val onItemClick: OnItemClick<T>?) : BaseObservable() {
    constructor(data: T) : this(data,null)

    open fun onClick(view: View) {
        onItemClick?.onItemClick(data)
    }

}