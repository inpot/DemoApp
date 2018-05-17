package app.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import app.base.di.IBuildComp
import app.base.di.component.ActivityComp
import app.base.di.component.DaggerFragmentComp
import app.base.di.component.FragmentComp
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import app.base.mvvm.vm.BaseVM
import app.base.widget.NoBgDialog

/**
 * Created by daniel on 18-1-26.
 */
abstract class BaseFragment : Fragment(), IBuildComp, IBaseView {

    private var loadingDialog: AppCompatDialog? = null
    lateinit var mFragmentComp: FragmentComp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildComp()
    }

    protected fun <B : ViewDataBinding, P : IPresenter, V : IView> bindViewModel(
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            layoutResId: Int,
            viewModel: BaseVM<P, V>): B {
        val binding = DataBindingUtil.inflate<B>(layoutInflater, layoutResId, container, false)
        viewModel.presenter.setLifecycleOwner(this)
        binding.setVariable(BR.vm, viewModel)
        if (ViewDataBinding.getBuildSdkInt() < Build.VERSION_CODES.KITKAT) {
            binding.executePendingBindings()
        }
        return binding
    }

    fun fragmentComp(): FragmentComp {
        if (!::mFragmentComp.isInitialized)
            mFragmentComp = DaggerFragmentComp.builder()
                    .activityComp(activityComp())
                    .build()
        return mFragmentComp
    }

    fun activityComp(): ActivityComp {
        val tmp = activity as BaseActivity
        return tmp.activityComp
    }

    @CallSuper
    override fun showError(e: Throwable) {
        e.printStackTrace()
    }

    @CallSuper
    override fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    @CallSuper
    override fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = onCreateLoadingDialog()
        }
        loadingDialog?.show()
    }

    override fun onCreateLoadingDialog(): AppCompatDialog? {
        val tmp  = context;
        return if (tmp != null) {
            val loadingDialog = NoBgDialog(tmp)
            loadingDialog.setContentView(ProgressBar(tmp))
            loadingDialog
        }else{
            null
        }
    }
}