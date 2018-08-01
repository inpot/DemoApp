<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
    <@kt.addAllKotlinDependencies />
    <merge from="root/AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    <merge from="root/res/values/manifest_strings.xml.ftl"
             to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

    <instantiate from="root/src/app_package/MainActivity.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />

    <instantiate from="root/src/app_package/MainRep.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/model/${moduleName?cap_first}Rep.kt" />
    <instantiate from="root/src/app_package/MainContract.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/di/${moduleName?cap_first}Contract.kt" />
    <instantiate from="root/src/app_package/MainModule.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/di/${moduleName?cap_first}Module.kt" />
    <instantiate from="root/src/app_package/MainVM.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${moduleName?cap_first}VM.kt" />
    <instantiate from="root/res/layout/activity_main.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
<#if viewType=="recyclerView">

    <instantiate from="root/src/app_package/ItemVM.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${moduleName?cap_first}ListItemVM.kt" />
    <instantiate from="root/src/app_package/SimpleAdapter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${moduleName?cap_first}ListAdapter.kt" />
    <instantiate from="root/res/layout/list_item.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/list_item_${moduleName?lower_case}.xml" />
    <open file="${escapeXmlAttribute(resOut)}/layout/list_item_${moduleName?lower_case}.xml" />

<#elseif viewType=="topPager">
    <instantiate from="root/src/app_package/SimplePagerAdapter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${moduleName?cap_first}PagerAdapter.kt" />
</#if>

    <open file="${escapeXmlAttribute(resOut)}/layout/activity_${moduleName?lower_case}.xml" />

    <open file="${escapeXmlAttribute(srcOut)}/${moduleName?cap_first}Activity.kt" />

<#if viewType=="recyclerView">
    <open file="${escapeXmlAttribute(resOut)}/layout/list_item_${moduleName?lower_case}.xml" />
</#if>

</recipe>
