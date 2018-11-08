package app.base.mvvm.vm.list

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.base.R
import app.base.view.OnItemClick
import app.base.widget.ILoadMore

/**
 * Created by daniel on 18-1-15.
 */
abstract class BaseListAdapter<D : Any> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: OnItemClick<D>? = null
    var loadMore: ILoadMore? = null
    private lateinit var footerBinding: ViewDataBinding
    var lists:MutableList<D> = mutableListOf()

    var footerType:Int = TYPE_FOOTER_LOADING
    set(value) {
        if (value !in TYPE_FOOTER_LOADING..TYPE_FOOTER_NONE){
            throw Throwable(" Type not right")
        }
        if (field == value) {
            return
        }
        field = value
        notifyItemChanged(itemCount -1)
    }


    fun clear() {
        lists.clear()
        notifyItemRangeRemoved(0,itemCount-1)
    }

    open fun addAll(listSet: MutableList<D>) {
        if(listSet.isEmpty()){
            return
        }
        val start = itemCount
        lists.addAll(listSet)
        val end = itemCount -1
        notifyItemRangeInserted(start, end)
    }

    open fun setData(listSet: MutableList<D>) {
        lists.clear()
        lists.addAll(listSet)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        var count =lists.size
        if (count > 0 && footerType != TYPE_FOOTER_NONE) {
            count += 1
        }
        return count
    }

    open fun createFooterView(footerType: Int, layoutInflater: LayoutInflater, parent: ViewGroup?): ViewDataBinding {
        footerBinding =
                when (footerType) {
                    TYPE_FOOTER_NO_MORE -> DataBindingUtil.inflate(layoutInflater, R.layout.footer_no_more_data, parent, false)
                    TYPE_FOOTER_LOADING -> DataBindingUtil.inflate(layoutInflater, R.layout.footer_loading, parent, false)
                    TYPE_FOOTER_ERROR-> DataBindingUtil.inflate(layoutInflater, R.layout.footer_loading, parent, false)
                    else -> DataBindingUtil.inflate(layoutInflater, R.layout.footer_loading, parent, false)
                }
        return footerBinding
    }

    override fun getItemViewType(position: Int):Int {
        var res = -1
        when(position){
            in 0 until lists.size -> { res = TYPE_CONTENT}
            lists.size -> { res = footerType }
        }
        return res
    }

    /*
   * @params
     */
    abstract fun onCreateItemBinding(layoutInflater: LayoutInflater, parent: ViewGroup?): ViewDataBinding

    abstract fun onCreateVM(position: Int, data: D): Any


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (holder is BaseViewHolder ) {
            when(viewType){
                TYPE_CONTENT -> holder.bindingVM(onCreateVM(position,lists[position]))
                TYPE_FOOTER_LOADING-> {loadMore?.onLoadMore()}
                TYPE_FOOTER_NO_MORE-> {}
                TYPE_FOOTER_ERROR -> {
                    //TODO retry
                }
                TYPE_FOOTER_NONE -> {}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context!!)
        return when (viewType) {
            TYPE_CONTENT -> BaseViewHolder(onCreateItemBinding(layoutInflater, parent))
            TYPE_HEADER -> BaseViewHolder(onCreateItemBinding(layoutInflater, parent))//TODO for emptyview or error
            in TYPE_FOOTER_LOADING..TYPE_FOOTER_NONE ->{BaseViewHolder(createFooterView(viewType,layoutInflater,parent))}
            else -> BaseViewHolder(onCreateItemBinding(layoutInflater, parent))
        }
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_CONTENT = 1
        const val TYPE_FOOTER_LOADING = 2
        const val TYPE_FOOTER_NO_MORE = 3
        const val TYPE_FOOTER_ERROR = 4
        const val TYPE_FOOTER_NONE = 5


    }

    abstract class BaseDiffCallback<out D>(val oldList:List<D>, val newList:List<D>): DiffUtil.Callback(){

        override fun getNewListSize() = newList.size

        override fun getOldListSize() = oldList.size

    }

}