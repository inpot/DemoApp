package app.base.mvvm.vm.list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import app.base.BR

/**
 * Created by daniel on 18-1-15.
 */
class BaseViewHolder (val binding:ViewDataBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindingVM(vm:Any) = binding.setVariable(BR.vm,vm)
}