package app.base.mvvm.vm

import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView

/**
 * Created by daniel on 17-10-19.
 */
abstract class BaseVM<out P : IPresenter, out V: IView> (val presenter:P, val view:V)