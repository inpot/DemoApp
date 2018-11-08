package com.example.daniel.module

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import android.text.TextUtils
import android.view.LayoutInflater
import app.base.mvvm.vm.list.BaseListAdapter
import android.view.ViewGroup
import com.example.daniel.myapplication.R
import com.example.daniel.tab.model.Book

class ForthListAdapter : BaseListAdapter<Book>() {

    override fun onCreateItemBinding(layoutInflater: LayoutInflater, parent: ViewGroup?): ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_forth, parent, false)

    override fun onCreateVM(position: Int, data: Book) = ForthListItemVM(data, onItemClick)

    /**
     * use DiffUtil to update ui when data change, dont modify
     */
    override fun addAll(listSet: MutableList<Book>) {
        val oldList = lists;
        listSet.addAll(0, lists)
        DiffUtil.calculateDiff(DiffCallBack(oldList, listSet)).dispatchUpdatesTo(this)
        lists = listSet
    }

    /**
     * use DiffUtil to update ui when data change, dont modify
     */
    override fun setData(listSet: MutableList<Book>) {
        val oldList = lists;
        DiffUtil.calculateDiff(DiffCallBack(oldList, listSet)).dispatchUpdatesTo(this)
        lists = listSet
    }

    class DiffCallBack(oldList: MutableList<Book>, newList: MutableList<Book>) : BaseListAdapter.BaseDiffCallback<Book>(oldList, newList) {

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return  oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return TextUtils.equals(oldItem.name,newItem.name)
        }


    }

}