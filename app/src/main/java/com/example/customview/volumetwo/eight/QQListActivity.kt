package com.example.customview.volumetwo.eight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customview.databinding.ActivityQqListBinding
import com.example.customview.volumetwo.eight.library.ItemTouchHelperExtension

class QQListActivity : AppCompatActivity() {
    private lateinit var activityQqListBinding: ActivityQqListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityQqListBinding = ActivityQqListBinding.inflate(layoutInflater)
        setContentView(activityQqListBinding.root)
        val mDatas = mutableListOf<String>().apply {
            for(i in 0 until 200){
                add("第${i}个Item")
            }
        }

        val mAdapter = QQListAdapter(mDatas)
        val callBack = QQItemTouchHelpCallBack(this@QQListActivity , mDatas , mAdapter)
        val mQQListItemTouchHelper = QQListItemTouchHelper(callBack)
        mAdapter.onDelete ={
            Toast.makeText(
                this,
                "点击Refresh",
                Toast.LENGTH_SHORT
            ).show()
            mAdapter.notifyItemRemoved(it.bindingAdapterPosition)
        }
        mAdapter.onRefresh = {
            Toast.makeText(
                this,
                "点击Delete",
                Toast.LENGTH_SHORT
            ).show()
        }
        activityQqListBinding.rv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@QQListActivity)
            addItemDecoration(DividerItemDecoration(this@QQListActivity , DividerItemDecoration.VERTICAL))
            mQQListItemTouchHelper.attachToRecyclerView(this)
        }
    }
}