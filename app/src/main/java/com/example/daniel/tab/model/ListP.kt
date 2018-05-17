package com.example.daniel.tab.model

import app.base.mvvm.presenter.BasePresenter
import javax.inject.Inject
import com.example.daniel.tab.di.ListContract

class ListP @Inject constructor() : BasePresenter(), ListContract.Presenter