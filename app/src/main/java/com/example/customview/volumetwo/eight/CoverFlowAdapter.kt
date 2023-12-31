package com.example.customview.volumetwo.eight

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.R
import com.example.customview.databinding.LayoutCoverflowItemBinding

class CoverFlowAdapter(
    val mDatas: List<Pair<String , Int>> = listOf(
        "第0个item" to R.drawable.item1 ,
        "第1个item" to R.drawable.item2 ,
        "第2个item" to R.drawable.item3 ,
        "第3个item" to R.drawable.item4 ,
        "第4个item" to R.drawable.item5 ,
        "第5个item" to R.drawable.item6 ,
    )
): RecyclerView.Adapter<CoverFlowAdapter.CoverFlowViewHolder>() {

    inner class CoverFlowViewHolder(layoutCoverflowItemBinding: LayoutCoverflowItemBinding): RecyclerView.ViewHolder(layoutCoverflowItemBinding.root){
        val coverFlowImage = layoutCoverflowItemBinding.ivCoverflow
        val coverFlowText = layoutCoverflowItemBinding.tvCoverflow
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverFlowViewHolder {
        return CoverFlowViewHolder(
            LayoutCoverflowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoverFlowViewHolder, position: Int) {
        holder.coverFlowImage.setImageResource(mDatas[position].second)
        holder.coverFlowText.setText(mDatas[position].first)
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }
}