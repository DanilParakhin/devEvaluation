package co.gotoinc.deveval.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import co.gotoinc.deveval.BR

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(baseViewModel: BaseObservable) {
        binding.apply {
            setVariable(BR.vm, baseViewModel)
            executePendingBindings()
        }
    }

}