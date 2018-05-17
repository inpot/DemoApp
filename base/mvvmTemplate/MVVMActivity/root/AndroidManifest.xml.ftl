<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${packageName}">

    <application>
        <activity android:name="${packageName}.${activityClass}"
            <#if generateActivityTitle!true>
                <#if isNewProject>
                    android:label="@string/app_name"
                <#else>
                    android:label="@string/title_${moduleName?lower_case}"
                </#if>
            </#if>
            <#if hasNoActionBar>
                android:theme="@style/${themeNameNoActionBar}"
            <#else>
                android:theme="@style/${themeName}"
            </#if>
            >
            <#if isLauncher>
                        <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            </#if>
        </activity>
    </application>
</manifest>
