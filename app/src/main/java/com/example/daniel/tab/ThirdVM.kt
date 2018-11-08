package com.example.daniel.tab

import androidx.fragment.app.FragmentStatePagerAdapter
import app.base.mvvm.vm.BaseVM
import com.example.daniel.tab.di.ListContract
import com.example.daniel.tab.di.ThirdContract

class ThirdVM(repository: ThirdContract.Repository,
              view: ThirdContract.View,
              val pagerAdapter: FragmentStatePagerAdapter
) : BaseVM<ThirdContract.Repository, ThirdContract.View>(repository, view)

