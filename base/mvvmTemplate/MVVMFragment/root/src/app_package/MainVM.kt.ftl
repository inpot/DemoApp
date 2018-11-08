package ${escapeKotlinIdentifiers(packageName)}

<#if viewType=="recyclerView">
import app.base.mvvm.vm.list.BaseListVM
import androidx.recyclerview.widget.RecyclerView
import app.base.view.OnItemClick
import app.base.mvvm.vm.list.BaseListAdapter
<#elseif viewType=="topPager">
import androidx.fragment.app.FragmentStatePagerAdapter
import app.base.mvvm.vm.BaseVM
<#else>
import app.base.mvvm.vm.BaseVM
</#if>
import ${escapeKotlinIdentifiers(packageName)}.di.${moduleName?cap_first}Contract

<#if viewType=="recyclerView">
class ${moduleName?cap_first}VM( ) :BaseListVM<${moduleName?cap_first}Contract.Repository, ${moduleName?cap_first}Contract.View, T>(),OnItemClick<T>{

    constructor(repository: ${moduleName?cap_first}Contract.Repository, view: ${moduleName?cap_first}Contract.View, layoutManager: RecyclerView.LayoutManager, adapter: BaseListAdapter<T>):this(){
        this.repository = repository
        this.view = view
        this.adapter = adapter
        this.layoutManager = layoutManager
    }

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
class ${moduleName?cap_first}VM() :PagerVM<${moduleName?cap_first}Contract.Repository, ${moduleName?cap_first}Contract.View >(){

    constructor(repository: ${moduleName?cap_first}Contract.Repository, view: ${moduleName?cap_first}Contract.View, pagerAdapter: FragmentStatePagerAdapter):this(){
        this.repository = repository
        this.view = view
        this.pagerAdapter = pagerAdapter
    }

}

<#else>
class ${moduleName?cap_first}VM() :BaseVM<${moduleName?cap_first}Contract.Repository, ${moduleName?cap_first}Contract.View >(){

    constructor(repository: ${moduleName?cap_first}Contract.Repository, view: ${moduleName?cap_first}Contract.View):this(){
        this.repository = repository
        this.view = view
    }

}
</#if>
