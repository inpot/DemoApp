package com.example.daniel.tab

import app.base.mvvm.vm.BaseVM
import com.example.daniel.tab.di.ListContract

class ListVM(presenter: ListContract.Presenter,
             view: ListContract.View
) : BaseVM<ListContract.Presenter, ListContract.View>(presenter, view)
