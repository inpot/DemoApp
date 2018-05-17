package app.base

import android.support.v7.app.AppCompatDialog

/**
 * Created by daniel on 18-1-31.
 */
interface IBaseView{
    fun showLoading()
    fun onCreateLoadingDialog():AppCompatDialog?
    fun dismissLoading()
    fun showError(e: Throwable)
}