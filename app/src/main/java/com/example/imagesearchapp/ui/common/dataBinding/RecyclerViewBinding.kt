package com.example.imagesearchapp.ui.common.dataBinding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("listData")
fun <T, VH : RecyclerView.ViewHolder> setItem(recyclerView: RecyclerView, items: List<T>?) {
    (recyclerView.adapter as? ListAdapter<T, VH>)?.let { adapter ->
        adapter.submitList(items)
        adapter.notifyDataSetChanged()
    }
}