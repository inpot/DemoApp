package com.example.daniel.tab

import app.base.mvvm.vm.BaseVM
import com.example.daniel.tab.di.ListContract

class ListVM(repository: ListContract.Repository,
             view: ListContract.View
) : BaseVM<ListContract.Repository, ListContract.View>(repository, view)
