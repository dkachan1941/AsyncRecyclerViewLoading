package com.example.recyclerasyncloadingexample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerasyncloadingexample.databinding.LargeItemCellBinding
import com.example.recyclerasyncloadingexample.databinding.SmallItemCellBinding

class RecyclerViewAsyncAdapter internal constructor(private val items: List<TestItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ViewTypes.SMALL.type -> SmallItemViewHolder(SmallItemCell(parent.context).apply { inflate() })
            else -> LargeItemViewHolder(LargeItemCell(parent.context).apply { inflate() })
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SmallItemViewHolder -> setUpSmallViewHolder(holder, position)
            is LargeItemViewHolder -> setUpLargeViewHolder(holder, position)
        }
    }

    private fun setUpLargeViewHolder(holder: LargeItemViewHolder, position: Int) {
        (holder.itemView as LargeItemCell).bindWhenInflated {
            items[position].let { item ->
                holder.itemView.binding?.item = item
            }
        }
    }

    private fun setUpSmallViewHolder(
        holder: SmallItemViewHolder,
        position: Int
    ) {
        (holder.itemView as SmallItemCell).bindWhenInflated {
            items[position].let { item ->
                holder.itemView.binding?.item = item
            }
        }
    }

    private inner class LargeItemViewHolder internal constructor(view: ViewGroup) :
        RecyclerView.ViewHolder(view)

    private inner class SmallItemViewHolder internal constructor(view: ViewGroup) :
        RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int = when {
        items[position].code % 2 == 0 -> ViewTypes.LARGE.type
        else -> ViewTypes.SMALL.type
    }

    private inner class LargeItemCell(context: Context) : AsyncCell(context) {
        var binding: LargeItemCellBinding? = null
        override val layoutId = R.layout.large_item_cell
        override fun createDataBindingView(view: View): View? {
            binding = LargeItemCellBinding.bind(view)
            return view.rootView
        }
    }

    private inner class SmallItemCell(context: Context) : AsyncCell(context) {
        var binding: SmallItemCellBinding? = null
        override val layoutId = R.layout.small_item_cell
        override fun createDataBindingView(view: View): View? {
            binding = SmallItemCellBinding.bind(view)
            return view.rootView
        }
    }
}