package app.base.mvvm.vm.list

import androidx.databinding.ObservableBoolean
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView
import app.base.mvvm.repository.IRepository
import app.base.mvvm.view.IView
import app.base.mvvm.vm.BaseVM
import app.base.widget.ILoadMore

/**
 * Created by daniel on 18-1-15.
 */
abstract class BaseListVM<Rep : IRepository, V : IView, D : Any>() :ILoadMore, BaseVM<Rep, V>() {
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: BaseListAdapter<D>

    constructor(repository: Rep, view: V, layoutManager: RecyclerView.LayoutManager, adapter: BaseListAdapter<D>):this(){
        this.repository = repository
        this.view = view
        this.adapter = adapter
        this.layoutManager = layoutManager
    }

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
            adapter.footerType = BaseListAdapter.TYPE_FOOTER_NO_MORE
        }else{
            adapter.footerType = BaseListAdapter.TYPE_FOOTER_LOADING
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
            adapter.footerType = BaseListAdapter.TYPE_FOOTER_ERROR
        }
    }

}

