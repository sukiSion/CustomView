package com.example.customview.volumetwo.eight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.customview.R
import com.example.customview.databinding.LayoutSnapItemBinding
import com.example.customview.volumetwo.eight.library.Extension

class SnapHelperAdapter(val length: Int): Adapter<SnapHelperAdapter.SnapHelperViewHolder>() {

    inner class SnapHelperViewHolder(val layoutSnapItemBinding: LayoutSnapItemBinding): ViewHolder(layoutSnapItemBinding.root) {
        val snapItemImage = layoutSnapItemBinding.ivSnapItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapHelperViewHolder {
        return SnapHelperViewHolder(
            LayoutSnapItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SnapHelperViewHolder, position: Int) {
        holder.snapItemImage.setImageResource(R.drawable.item1)
    }

    override fun getItemCount(): Int  = length
}