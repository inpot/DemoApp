package com.example.daniel.module

import app.base.mvvm.vm.list.BaseItemVM
import app.base.view.OnItemClick
import com.example.daniel.tab.model.Book

class ForthListItemVM(data: Book, onItemClick: OnItemClick<Book>?) : BaseItemVM<Book>(data)
