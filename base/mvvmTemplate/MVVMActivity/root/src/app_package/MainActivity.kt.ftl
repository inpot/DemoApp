package ${escapeKotlinIdentifiers(packageName)}

import android.os.Bundle
import app.base.BaseActivity
import ${applicationPackage}.databinding.Activity${moduleName?cap_first}Binding
import ${escapeKotlinIdentifiers(packageName)}.di.Dagger${moduleName?cap_first}Contract_Comp
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Contract
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Module
import javax.inject.Inject
import ${applicationPackage}.R

class ${activityClass} : BaseActivity(),${moduleName?cap_first}Contract.View {

    @Inject
    lateinit var vm: ${moduleName?cap_first}VM

    override fun buildComp() {
        Dagger${moduleName?cap_first}Contract_Comp.builder().activityComp(activityComp).${moduleName?uncap_first}Module(${moduleName?cap_first}Module(this))
                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:Activity${moduleName}Binding =  bindViewModel(R.layout.${layoutName},vm,${isHomeAsUp?c})
        binding.setLifecycleOwner(this)
    }

}
