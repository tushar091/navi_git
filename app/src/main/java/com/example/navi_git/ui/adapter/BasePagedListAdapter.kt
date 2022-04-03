package com.example.navi_git.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navi_git.BR

abstract class BasePagedListAdapter<T : IRecyclerViewItem>(diffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BasePagedListAdapter.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        val viewHolder = ViewHolder(binding)
        registerItemClickListener(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getLayoutId() ?: 0
    }

    abstract fun registerItemClickListener(viewHolder: ViewHolder)

    class ViewHolder internal constructor(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Any?) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }

    }
}