package app.base.mvvm.vm.list

import android.databinding.ObservableBoolean
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import app.base.mvvm.presenter.IPresenter
import app.base.mvvm.view.IView
import app.base.mvvm.vm.BaseVM
import app.base.widget.ILoadMore

/**
 * Created by daniel on 18-1-15.
 */
abstract class BaseListVM<out P : IPresenter, out V : IView, D : Any>(presenter: P,
                                                                      view: V,
                                                                      val layoutManager: RecyclerView.LayoutManager,
                                                                      val adapter: BaseListAdatper<D>) :ILoadMore, BaseVM<P, V>(presenter, view) {
    open var PAGE_SIZE = 25
    private var currentPage = 0
    var refreshing = ObservableBoolean(false)
    var loading = false

    val refreshingListener = SwipeRefreshLayout.OnRefreshListener {
        currentPage = 0
        loadData()
    }

    init {
        adapter.loadMore = this
    }

    fun loadData() {
        if (loading) {
            return
        }
        loading = true
        onLoadData(currentPage)
    }

    override fun onLoadMore() {
        if (loading) {
            return
        }
        currentPage++
        onLoadData(currentPage)
    }

    abstract fun onLoadData(page: Int)


    fun bindResult(result: List<D>?) {
        refreshing.set(false)
        val size = result?.size ?: 0
        if (size < PAGE_SIZE) {
            adapter.footerType = BaseListAdatper.TYPE_FOOTER_NO_MORE
        }else{
            adapter.footerType = BaseListAdatper.TYPE_FOOTER_LOADING
        }

        if(result != null && size > 0){
            val listSet = mutableListOf<D>()
            listSet.addAll(result)
            if(currentPage  == 0){
                adapter.setData(listSet)
            }else{
                adapter.addAll(listSet)
            }
        }
        loading = false
    }

    fun bindError(errorCode: Int, msg: String) {
        loading = false
        refreshing.set(false)
        if(currentPage>0){
            adapter.footerType = BaseListAdatper.TYPE_FOOTER_ERROR
        }
    }

}

