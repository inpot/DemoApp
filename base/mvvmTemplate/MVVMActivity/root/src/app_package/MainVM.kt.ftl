package ${escapeKotlinIdentifiers(packageName)}

<#if viewType=="recyclerView">
import app.base.mvvm.vm.list.BaseListVM
import android.support.v7.widget.RecyclerView
import app.base.view.OnItemClick
import app.base.mvvm.vm.list.BaseListAdatper
<#elseif viewType=="topPager">
import android.support.v4.app.FragmentStatePagerAdapter
import app.base.mvvm.vm.BaseVM
<#else>
import app.base.mvvm.vm.BaseVM
</#if>
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Contract

<#if viewType=="recyclerView">
class ${moduleName?cap_first}VM(presenter: ${moduleName?cap_first}Contract.Presenter,
             view: ${moduleName?cap_first}Contract.View,
             layoutManager: RecyclerView.LayoutManager,
             adapter: BaseListAdatper<T>
) :BaseListVM<${moduleName?cap_first}Contract.Presenter, ${moduleName?cap_first}Contract.View, T>(presenter, view, layoutManager, adapter),OnItemClick<T>{

    init {
       adapter.onItemClick = this
    }

    override fun onLoadData(page: Int) {
        TODO()
    }

    override fun onItemClick(data: T){
        TODO()
    }
}

<#elseif viewType=="topPager">
class ${moduleName?cap_first}VM(presenter: ${moduleName?cap_first}Contract.Presenter,
             view: ${moduleName?cap_first}Contract.View,
             val pagerAdapter: FragmentStatePagerAdapter
) :BaseVM<${moduleName?cap_first}Contract.Presenter, ${moduleName?cap_first}Contract.View >(presenter, view)

<#else>
class ${moduleName?cap_first}VM(presenter: ${moduleName?cap_first}Contract.Presenter,
             view: ${moduleName?cap_first}Contract.View
) :BaseVM<${moduleName?cap_first}Contract.Presenter, ${moduleName?cap_first}Contract.View >(presenter, view)
</#if>