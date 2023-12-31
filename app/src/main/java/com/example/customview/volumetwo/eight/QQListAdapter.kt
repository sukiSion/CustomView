package com.example.customview.volumetwo.eight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.customview.databinding.LayoutQqItemBinding
import com.example.customview.volumetwo.eight.library.Extension

class QQListAdapter(
    val mDatas: MutableList<String>,
    var onDelete: ((qqListViewHolder: QQListViewHolder) -> Unit)? = null,
    var onRefresh: ((qqListViewHolder: QQListViewHolder) -> Unit) ? = null
):  Adapter<QQListAdapter.QQListViewHolder>(){


    inner class QQListViewHolder(val layoutQqItemBinding: LayoutQqItemBinding): ViewHolder(layoutQqItemBinding.root) , Extension{
        val itemText = layoutQqItemBinding.tvItem
        val itemDalete = layoutQqItemBinding.tvDelete
        val itemRefresh = layoutQqItemBinding.tvRefresh

        override fun getActionWidth(): Float {
            return layoutQqItemBinding.llActionList.width.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QQListViewHolder {
        return QQListViewHolder(
            LayoutQqItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QQListViewHolder, position: Int) {
        holder.itemText.setText(mDatas[position])
        holder.itemDalete.setOnClickListener {
            mDatas.removeAt(position)
            onDelete?.invoke(holder)
        }
        holder.itemRefresh.setOnClickListener {
            onRefresh?.invoke(holder)
        }
    }

    override fun getItemCount(): Int = mDatas.size
}