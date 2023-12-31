package com.example.customview.volumetwo.eight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customview.R
import com.example.customview.databinding.ActivityCoverFlowRecyclerViewBinding

class CoverFlowRecyclerViewActivity : AppCompatActivity() {
    private lateinit var activityCoverFlowRecyclerViewBinding: ActivityCoverFlowRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCoverFlowRecyclerViewBinding = ActivityCoverFlowRecyclerViewBinding.inflate(layoutInflater)
        setContentView(activityCoverFlowRecyclerViewBinding.root)
        val mData = mutableListOf<Pair<String  , Int>>().apply{
            for(i in 0 until 200){
                add("第${i}个item" to R.drawable.item1)
            }
        }
        activityCoverFlowRecyclerViewBinding.crv.apply {
            layoutManager = CoverFlowLayoutManager()
            adapter = CoverFlowAdapter(mData)

        }
    }
}