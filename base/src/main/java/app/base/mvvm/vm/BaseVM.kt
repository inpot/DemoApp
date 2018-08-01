package app.base.mvvm.vm

import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView

/**
 * Created by daniel on 17-10-19.
 */
abstract class BaseVM<out Rep : IRepository, out V: IView> (val repository:Rep, val view:V)