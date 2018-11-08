package app.base.mvvm.vm

import androidx.fragment.app.FragmentStatePagerAdapter
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView

class PagerVM<Rep : IRepository, V: IView, D : FragmentStatePagerAdapter>() :BaseVM<Rep,V>(){

    lateinit var pagerAdapter: D
    constructor(repository:Rep,view:V, pagerAdapter: D) : this() {
        this.repository = repository
        this.view = view
        this.pagerAdapter = pagerAdapter
    }
}