package ${escapeKotlinIdentifiers(packageName)}

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup
import app.base.mvvm.vm.list.BaseListAdapter
import ${applicationPackage}.R

class ${moduleName?cap_first}ListAdapter : BaseListAdapter<T>() {

    override fun onCreateItemBinding(layoutInflater: LayoutInflater, parent: ViewGroup?): ViewDataBinding
            = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_${moduleName?lower_case}, parent, false)

    override fun onCreateVM(position: Int, data:T) = ${moduleName?cap_first}ListItemVM(data, onItemClick)

    /**
    * use DiffUtil to update ui when data change, dont modify
    */
    override fun addAll(listSet: MutableList<T>) {
        val oldList = lists;
        listSet.addAll(0, lists)
        DiffUtil.calculateDiff(DiffCallBack(oldList, listSet)).dispatchUpdatesTo(this)
        lists = listSet
    }

    /**
    * use DiffUtil to update ui when data change, dont modify
    */
    override fun setData(listSet: MutableList<T>) {
        val oldList = lists;
        DiffUtil.calculateDiff(DiffCallBack(oldList, listSet)).dispatchUpdatesTo(this)
        lists = listSet
    }

    class DiffCallBack(oldList: MutableList<T>, newList: MutableList<T>) : BaseListAdapter.BaseDiffCallback<T>(oldList, newList) {

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            TODO() // must implement this diff callback
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            TODO() // must implement this diff callback
        }


    }

}
