package ${escapeKotlinIdentifiers(packageName)}.model

import app.base.mvvm.repository.BaseRepository
import javax.inject.Inject
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Contract

class ${moduleName?cap_first}Rep @Inject constructor() : BaseRepository(), ${moduleName?cap_first}Contract.Repository