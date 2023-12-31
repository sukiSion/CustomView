package com.example.customview.volumetwo.seven

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.databinding.LayoutGridItemBinding

class GridRecyclerAdapter(
    val contents: List<String>
): RecyclerView.Adapter<GridRecyclerAdapter.GridViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(
            LayoutGridItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.gridItemText.setText(contents[position])
    }

    override fun getItemCount(): Int {return contents.size}

    inner class GridViewHolder(layoutGridItemBinding: LayoutGridItemBinding): RecyclerView.ViewHolder(layoutGridItemBinding.root){
        val gridItemText = layoutGridItemBinding.tvGridItem
    }
}