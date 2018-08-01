package com.example.daniel.module

import app.base.mvvm.vm.BaseVM
import com.example.daniel.module.di.SevenContract

class SevenVM(repository: SevenContract.Repository,
              view: SevenContract.View
) : BaseVM<SevenContract.Repository, SevenContract.View>(repository, view)
