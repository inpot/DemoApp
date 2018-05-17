package app.base.view

/**
 * Created by daniel on 18-3-5.
 */
interface OnItemClick<in T>{

    fun onItemClick(data:T)
}