package app.base

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import app.base.di.IBuildComp
import app.base.di.component.ActivityComp
import app.base.di.component.DaggerActivityComp
import app.base.di.modules.ActivityModule
import app.base.mvvm.repository.IRepository
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

    protected fun <B : ViewDataBinding, P : IRepository, V : IView> bindViewModel(layoutResId: Int, viewModel: BaseVM<P, V>, homeAsUp: Boolean): B {
        val binding = DataBindingUtil.setContentView<B>(this, layoutResId)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.apply {
            subtitle = ""
            setSupportActionBar(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUp)
        viewModel.repository.setLifecycleOwner(this)
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
    lateinit var  imm : InputMethodManager

    override fun onPause() {
        super.onPause()
        if(!::imm.isInitialized){
            imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }
//        imm.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

}