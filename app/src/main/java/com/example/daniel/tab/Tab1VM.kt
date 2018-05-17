package com.example.daniel.tab

import app.base.mvvm.vm.BaseVM
import com.example.daniel.tab.di.Tab1Contract

class Tab1VM(presenter: Tab1Contract.Presenter,
             view: Tab1Contract.View
) : BaseVM<Tab1Contract.Presenter, Tab1Contract.View>(presenter, view)
