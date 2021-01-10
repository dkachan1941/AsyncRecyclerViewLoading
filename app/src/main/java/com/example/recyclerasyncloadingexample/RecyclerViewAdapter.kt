package com.example.recyclerasyncloadingexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerasyncloadingexample.databinding.LargeItemCellBinding
import com.example.recyclerasyncloadingexample.databinding.SmallItemCellBinding

class RecyclerViewAdapter internal constructor(private val items: List<TestItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ViewTypes.SMALL.type -> SmallItemViewHolder(
                SmallItemCellBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> LargeItemViewHolder(
                LargeItemCellBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SmallItemViewHolder -> setUpSmallViewHolder(holder, position)
            is LargeItemViewHolder -> setUpLargeViewHolder(holder, position)
        }
    }

    private fun setUpLargeViewHolder(holder: LargeItemViewHolder, position: Int) {
        items[position].let { item ->
            holder.binding.item = item
        }
    }

    private fun setUpSmallViewHolder(
        holder: SmallItemViewHolder,
        position: Int
    ) {
        items[position].let { item ->
            holder.binding.item = item
        }
    }

    private inner class LargeItemViewHolder internal constructor(val binding: LargeItemCellBinding) :
        RecyclerView.ViewHolder(binding.root)

    private inner class SmallItemViewHolder internal constructor(val binding: SmallItemCellBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int = when {
        items[position].code % 2 == 0 -> ViewTypes.LARGE.type
        else -> ViewTypes.SMALL.type
    }
}

enum class ViewTypes(val type: Int) {
    LARGE(0),
    SMALL(1)
}