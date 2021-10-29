package com.worldline.kmm.android

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * RootAdapter
 */
abstract class RootAdapter<T, B : ViewBinding>(
    protected val items: MutableList<T> = mutableListOf(),
    private val onItemClickListener: (T) -> Unit = {},
    private val onLongClickListener: (T) -> Unit = {}
) : RecyclerView.Adapter<RootAdapter.RootViewHolder<T, B>>() {

    abstract fun viewHolder(parent: ViewGroup): RootViewHolder<T, B>

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder<T, B> {
        val viewHolder = viewHolder(parent)
        viewHolder.onItemClickListener = { onItemClickListener(items[it]) }
        viewHolder.onLongClickListener = { onLongClickListener(items[it]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RootViewHolder<T, B>, position: Int) {
        holder.bind(items[position])
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(indexOf(item))
    }

    fun remove(item: T) {
        if (items.contains(item)) {
            val index = items.indexOf(item)
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun update(item: T) {
        if (items.contains(item)) {
            val index = items.indexOf(item)
            items[index] = item
            notifyItemChanged(index)
        }
    }

    fun addIfNotExist(index: Int, item: T): Boolean {
        return if (!items.contains(item)) {
            items.add(index, item)
            notifyItemInserted(indexOf(item))
            true
        } else {
            false
        }
    }

    fun clear() {
        notifyItemRangeRemoved(0, itemCount)
        items.clear()
    }

    fun addAll(newItems: List<T>) {
        newItems.forEach { add(it) }
    }

    fun replace(newItems: MutableList<T>) {
        clear()
        addAll(newItems)
    }

    fun indexOf(item: T) = items.indexOf(item)

    fun removeOldItems(newItems: List<T>) {
        val candidates = items.filter { !newItems.contains(it) }
        candidates.forEach { remove(it) }
    }

    fun addAllOrUpdate(items: List<T>) {
        items.forEach {
            when (this.items.contains(it)) {
                true -> update(it)
                false -> add(it)
            }
        }
    }

    abstract class RootViewHolder<in T, B : ViewBinding>(
        itemView: B,
        var onItemClickListener: (Int) -> Unit = {},
        var onLongClickListener: (Int) -> Unit = {}
    ) : RecyclerView.ViewHolder(itemView.root) {

        init {
            itemView.root.setOnClickListener { onItemClickListener(adapterPosition) }
            itemView.root.setOnLongClickListener {
                onLongClickListener(adapterPosition)
                true
            }
        }

        abstract fun bind(model: T)
    }
}