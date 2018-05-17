package app.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialog
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ProgressBar
import app.base.di.IBuildComp
import app.base.di.component.ActivityComp
import app.base.di.component.DaggerActivityComp
import app.base.di.modules.ActivityModule
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import app.base.mvvm.vm.BaseVM
import app.base.widget.NoBgDialog

/**
 * Created by daniel on 17-11-28.
 * base activity for all activity that can use dagger  and databinding
 */
abstract class BaseActivity : AppCompatActivity(), IBuildComp, IBaseView {
    lateinit var activityComp: ActivityComp
    private var loadingDialog: AppCompatDialog? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as BaseApplication
        activityComp = DaggerActivityComp.builder()
                .appComp(application.appComp)
                .activityModule(ActivityModule(this))
                .build()
        buildComp()
    }

    @CallSuper
    override fun showError(e: Throwable){
        e.printStackTrace()
    }

    @CallSuper
    override fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    @CallSuper
    override fun showLoading() {
        if(loadingDialog == null){
            loadingDialog = onCreateLoadingDialog()
        }
        loadingDialog?.show()
    }

    override fun onCreateLoadingDialog(): AppCompatDialog? {
        val loadingDialog = NoBgDialog(this)
        loadingDialog.setContentView(ProgressBar(this))
        return loadingDialog
    }

    /**
     *  bind viewmodel to viewbinding
     *  @param homeAsUp for back button in toolbar
     *
     * */

    protected fun <B : ViewDataBinding, P : IPresenter, V : IView> bindViewModel(layoutResId: Int, viewModel: BaseVM<P, V>, homeAsUp: Boolean): B {
        val binding = DataBindingUtil.setContentView<B>(this, layoutResId)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.apply {
            subtitle = ""
            setSupportActionBar(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUp)
        viewModel.presenter.setLifecycleOwner(this)
        binding.setVariable(BR.vm, viewModel)
        if (ViewDataBinding.getBuildSdkInt() < Build.VERSION_CODES.KITKAT) {
            binding.executePendingBindings()
        }
        return binding
    }

    /**
     * set back menu action for all activity
     *
     * **/
    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.run {
            if (this.itemId == android.R.id.home) {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}