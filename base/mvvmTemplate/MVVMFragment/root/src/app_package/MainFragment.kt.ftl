package ${escapeKotlinIdentifiers(packageName)}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.base.BaseFragment
import ${applicationPackage}.databinding.Fragment${moduleName?cap_first}Binding
import ${escapeKotlinIdentifiers(packageName)}.di.Dagger${moduleName?cap_first}Contract_Comp
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Contract
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Module
import javax.inject.Inject
import ${applicationPackage}.R

class ${fragmentClass}: BaseFragment(),${moduleName?cap_first}Contract.View {

    @Inject
    lateinit var vm: ${moduleName?cap_first}VM

    override fun buildComp() {
        Dagger${moduleName?cap_first}Contract_Comp.builder().activityComp(activityComp()).${moduleName?uncap_first}Module(${moduleName?cap_first}Module(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: Fragment${moduleName?cap_first}Binding = bindViewModel(inflater,container,R.layout.${layoutName}, vm)
        return binding.root
    }

}
