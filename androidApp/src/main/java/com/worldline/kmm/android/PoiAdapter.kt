package com.worldline.kmm.android

import android.view.LayoutInflater
import android.view.ViewGroup
import com.worldline.kmm.android.databinding.ItemPoiBinding
import com.worldline.kmm.core.Poi

class PoiAdapter() : RootAdapter<Poi, ItemPoiBinding>() {

    override fun viewHolder(parent: ViewGroup): RootViewHolder<Poi, ItemPoiBinding> =
        PoiViewHolder(ItemPoiBinding.inflate(LayoutInflater.from(parent.context)))

    class PoiViewHolder(private val binding: ItemPoiBinding) :
        RootViewHolder<Poi, ItemPoiBinding>(binding) {

        override fun bind(model: Poi) {
            binding.title.text = model.title
        }
    }


}