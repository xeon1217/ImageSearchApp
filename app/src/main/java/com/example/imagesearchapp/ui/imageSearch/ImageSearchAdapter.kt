package com.example.imagesearchapp.ui.imageSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchapp.R
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchDocumentVO
import com.example.imagesearchapp.databinding.ItemImageBinding
import com.example.imagesearchapp.ui.imageSearchDetail.ImageSearchDetailActivity

class ImageSearchAdapter :
    ListAdapter<ImageSearchDocumentVO, ImageSearchAdapter.ViewHolder>(
        ImageSearchListDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    //잘못 입력하거나 없을 경우 오류 발생!
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_image
    }

    override fun onBindViewHolder(holder: ImageSearchAdapter.ViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(document: ImageSearchDocumentVO) {
            binding.document = document
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                binding.root.context.startActivity( Intent(binding.root.context, ImageSearchDetailActivity::class.java).putExtra("document", document))
            }
        }
    }
}

private class ImageSearchListDiffCallback :
    DiffUtil.ItemCallback<ImageSearchDocumentVO>() {
    override fun areItemsTheSame(
        oldItem: ImageSearchDocumentVO,
        newItem: ImageSearchDocumentVO
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ImageSearchDocumentVO,
        newItem: ImageSearchDocumentVO
    ): Boolean {
        return oldItem == newItem
    }
}