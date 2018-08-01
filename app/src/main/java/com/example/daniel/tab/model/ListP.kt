package com.example.daniel.tab.model

import app.base.mvvm.repository.BaseRepository
import javax.inject.Inject
import com.example.daniel.tab.di.ListContract

class ListP @Inject constructor() : BaseRepository(), ListContract.Repository