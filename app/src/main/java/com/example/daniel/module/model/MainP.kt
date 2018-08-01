package com.example.daniel.module.model

import app.base.mvvm.repository.BaseRepository
import javax.inject.Inject
import com.example.daniel.module.di.MainContract

class MainRep @Inject constructor() : BaseRepository(), MainContract.Repository