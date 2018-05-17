package ${escapeKotlinIdentifiers(packageName)}.di

<#if viewType=="recyclerView">
import android.support.v7.widget.RecyclerView
import app.base.di.modules.LayoutManagerModules
import app.base.di.scope.ListType
import ${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}ListAdapter
<#elseif viewType=="topPager">
import android.support.v4.app.FragmentManager
import ${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}PagerAdapter
</#if>
import app.base.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import ${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}VM
import ${escapeKotlinIdentifiers(packageName)}.model.${moduleName?cap_first}P

<#if viewType=="recyclerView">
@Module(includes = [LayoutManagerModules::class])
<#else>
@Module
</#if>
class ${moduleName?cap_first}Module(val view:${moduleName?cap_first}Contract.View){

<#if viewType=="recyclerView">
    @Provides
    @PerActivity
    fun provideVM(presenterP: ${moduleName?cap_first}P, @Named(ListType.VERTICAL) layoutManager:RecyclerView.LayoutManager)
            = ${moduleName?cap_first}VM(presenterP,view,layoutManager, ${moduleName?cap_first}ListAdapter())

<#elseif viewType=="topPager">
    @Provides
    @PerActivity
    fun provideVM(presenterP: ${moduleName?cap_first}P,fragmentManager:FragmentManager)
            = ${moduleName?cap_first}VM(presenterP,view, ${moduleName?cap_first}PagerAdapter(fragmentManager))

<#else>
    @Provides
    @PerActivity
    fun provideVM(presenterP: ${moduleName?cap_first}P)
            = ${moduleName?cap_first}VM(presenterP,view)

</#if>
}