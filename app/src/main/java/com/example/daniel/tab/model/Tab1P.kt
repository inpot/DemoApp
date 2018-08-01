package com.example.daniel.tab.model

import app.base.mvvm.repository.BaseRepository
import javax.inject.Inject
import com.example.daniel.tab.di.Tab1Contract

class Tab1P @Inject constructor() : BaseRepository(), Tab1Contract.Repository