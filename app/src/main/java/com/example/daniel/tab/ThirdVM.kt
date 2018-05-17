package com.example.daniel.tab

import android.support.v4.app.FragmentStatePagerAdapter
import app.base.mvvm.vm.BaseVM
import com.example.daniel.tab.di.ThirdContract

class ThirdVM(presenter: ThirdContract.Presenter,
              view: ThirdContract.View,
              val pagerAdapter: FragmentStatePagerAdapter
) : BaseVM<ThirdContract.Presenter, ThirdContract.View>(presenter, view)

