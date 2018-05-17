package ${escapeKotlinIdentifiers(packageName)}.model

import app.base.mvvm.presenter.BasePresenter
import javax.inject.Inject
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Contract

class ${moduleName?cap_first}P @Inject constructor() : BasePresenter(), ${moduleName?cap_first}Contract.Presenter