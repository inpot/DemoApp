package ${escapeKotlinIdentifiers(packageName)}.di

<#if viewType=="recyclerView">
import androidx.recyclerview.widget.RecyclerView
import app.base.di.modules.LayoutManagerModules
import app.base.di.scope.ListType
import ${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}ListAdapter
<#elseif viewType=="topPager">
import androidx.fragment.app.FragmentManager
import ${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}PagerAdapter
</#if>

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import ${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}VM
import ${escapeKotlinIdentifiers(packageName)}.model.${moduleName?cap_first}Rep

<#if viewType=="recyclerView">
@Module(includes = [LayoutManagerModules::class])
<#else>
@Module
</#if>
class ${moduleName?cap_first}Module(val view:${moduleName?cap_first}Contract.View){

<#if viewType=="recyclerView">
    @Provides
    @PerActivity
    fun provideVM(repository: ${moduleName?cap_first}Rep, @Named(ListType.VERTICAL) layoutManager:RecyclerView.LayoutManager):${moduleName?cap_first}ListVM{
        var vm =
        when (view){
            is Fragment -> ViewModelProviders.of(view).get(${moduleName?cap_first}VM::class.java)
            is FragmentActivity -> ViewModelProviders.of(view).get(${moduleName?cap_first}VM::class.java)
            else -> ${moduleName?cap_first}VM(repository,view, layoutManager, ${moduleName?cap_first}ListAdapter())
        }
        if(!vm.isInitialized()){
            vm.view = view
            vm.repository = repository
            vm.layoutManager =  layoutManager
            vm.adapter = ${moduleName?cap_first}ListAdapter()
        }
        return vm
    }

<#elseif viewType=="topPager">
    @Provides
    @PerActivity
    fun provideVM(repository: ${moduleName?cap_first}Rep,fragmentManager:FragmentManager):${moduleName?cap_first}PagerVM{
        var vm =
        when (view){
            is Fragment -> ViewModelProviders.of(view).get(${moduleName?cap_first}VM::class.java)
            is FragmentActivity -> ViewModelProviders.of(view).get(${moduleName?cap_first}VM::class.java)
            else -> ${moduleName?cap_first}VM(repository,view, ${moduleName?cap_first}PagerAdapter(fragmentManager))
        }
        if(!vm.isInitialized()){
            vm.view = view
            vm.repository = repository
            vm.pagerAdapter = ${moduleName?cap_first}PagerAdapter(fragmentManager)
        }
        return vm
    }

<#else>
    @Provides
    @PerActivity
    fun provideVM(repository: ${moduleName?cap_first}Rep):${moduleName?cap_first}VM{
        var vm =
        when (view){
            is Fragment -> ViewModelProviders.of(view).get(${moduleName?cap_first}VM::class.java)
            is FragmentActivity -> ViewModelProviders.of(view).get(${moduleName?cap_first}VM::class.java)
            else -> ${moduleName?cap_first}VM(repository, view)
        }
        if(!vm.isInitialized()){
            vm.view = view
            vm.repository = repository
        }
        return vm
    }

</#if>
}
