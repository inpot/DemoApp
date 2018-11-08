package com.example.daniel.module

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import app.base.mvvm.vm.list.BaseListAdapter
import com.example.daniel.myapplication.R

class RecyclerListAdapter : BaseListAdapter<String>() {

    override fun onCreateItemBinding(layoutInflater: LayoutInflater, parent: ViewGroup?): ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_recycler, parent, false)

    override fun onCreateVM(position: Int, data: String) = RecyclerListItemVM(data)

    override fun addAll(listSet: MutableList<String>) {
        val oldList = lists;
        listSet.addAll(0, lists)
        DiffUtil.calculateDiff(DiffCallBack(oldList, listSet)).dispatchUpdatesTo(this)
        lists = listSet
    }

    override fun setData(listSet: MutableList<String>) {
        val oldList = lists;
        DiffUtil.calculateDiff(DiffCallBack(oldList, listSet)).dispatchUpdatesTo(this)
        lists = listSet
    }

    class DiffCallBack(oldList: MutableList<String>, newList: MutableList<String>) : BaseListAdapter.BaseDiffCallback<String>(oldList, newList) {

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return TextUtils.equals(oldItem, newItem)
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return TextUtils.equals(oldItem, newItem)
        }


    }

}

