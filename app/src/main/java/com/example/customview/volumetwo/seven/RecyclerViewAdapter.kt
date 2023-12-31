package com.example.customview.volumetwo.seven

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.MyApplication
import com.example.customview.databinding.LayoutItemBinding
import com.example.customview.databinding.LayoutSecondItemBinding

class RecyclerViewAdapter(
    private val contents: List<String>,
    var onDrag: ((RecyclerViewHolder) -> Unit)? = null,
    var onSwipe: ((RecyclerViewHolder) -> Unit)? = null
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    private val heights by lazy {
         mutableListOf<Int>().also {
             for(i in contents.indices){
                 it.add((Math.random() * 300).toInt())
             }
         }
    }

    private var mCreatedHolder = 0

    inner class RecyclerViewHolder(layoutItemBinding: LayoutItemBinding): RecyclerView.ViewHolder(layoutItemBinding.root){
        val tvItem = layoutItemBinding.tvItem
        val ivDrag = layoutItemBinding.ivDrag
        init {
            layoutItemBinding.tvItem.let {
                item ->
                item.setOnClickListener {
                    Toast.makeText(MyApplication.appContext ,item.text , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    inner class RecyclerSecondViewHolder(layoutSecondItemBinding: LayoutSecondItemBinding): RecyclerView.ViewHolder(layoutSecondItemBinding.root){
        val tvSecondItem =  layoutSecondItemBinding.tvSecondItem
    }

    enum class ITEM_TYPE{
        ITEM_TYPE_SECTION,
        ITEM_TYPE_ITEM
    }

    override fun getItemViewType(position: Int): Int {
//        if(position % 10 == 0){
//            return ITEM_TYPE.ITEM_TYPE_SECTION.ordinal
//        }else{
            return ITEM_TYPE.ITEM_TYPE_ITEM.ordinal
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mCreatedHolder++
        Log.e("Sion" , "onCreateViewHolder num: ${mCreatedHolder}")
        val viewHolder = if(viewType == ITEM_TYPE.ITEM_TYPE_ITEM.ordinal){
            RecyclerViewHolder(LayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        }else{
            RecyclerSecondViewHolder(
                LayoutSecondItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("Sion" , "onBindViewHolder")
        when(holder){
            is RecyclerViewHolder ->  {
                holder.tvItem.setText(contents[position])
                holder.ivDrag.setOnTouchListener { v, event ->
                    if(event.action == MotionEvent.ACTION_DOWN){
                        onDrag?.invoke(holder)
                    }
                    return@setOnTouchListener false
                }
            }
            is RecyclerSecondViewHolder ->{
                holder.tvSecondItem.setText("第${position / 10}组")
            }
        }
    }

    override fun getItemCount(): Int {
        return contents.count()
    }
}