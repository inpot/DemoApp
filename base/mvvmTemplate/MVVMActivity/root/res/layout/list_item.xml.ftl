<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <import type="${escapeKotlinIdentifiers(packageName)}.${moduleName?cap_first}ListItemVM" />

        <variable
            name="vm"
            type="${moduleName?cap_first}ListItemVM" />
    </data>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="8dp"
        android:text="@{vm.data}"
        android:textSize="16dp" />
</layout>
