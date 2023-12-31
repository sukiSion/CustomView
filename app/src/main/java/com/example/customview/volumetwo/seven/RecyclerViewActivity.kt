package com.example.customview.volumetwo.seven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.customview.R
import com.example.customview.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var activityRecyclerViewBinding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecyclerViewBinding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(activityRecyclerViewBinding.root)
        val contents = mutableListOf<String>().also {
            for(i in 0 until 200){
                it.add("第${i}个item")
            }
        }
        activityRecyclerViewBinding.rv.let {

//            it.layoutManager = CustomLayoutManager()
            it.layoutManager  = LinearLayoutManager(this)
//            it.layoutManager = GridLayoutManager(this , 5 , GridLayoutManager.VERTICAL ,false)
//            it.layoutManager = StaggeredGridLayoutManager(5 , StaggeredGridLayoutManager.VERTICAL)
            it.addItemDecoration(DividerItemDecoration(this , DividerItemDecoration.VERTICAL))
//            it.addItemDecoration(DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL))
//            it.addItemDecoration(CustomItemDecoration(this))
//            it.layoutDirection = View.LAYOUT_DIRECTION_RTL
            RecyclerViewAdapter(contents){}.also {
                adapter ->
                it.adapter =  adapter
                ItemTouchHelper(
                    CustomItemTouchHelperCallBack(
                        contents,
                        adapter
                    )
                ).also {
                    itemTouchHelper ->
                    adapter.onDrag = {
                        viewHolder ->
                        itemTouchHelper.startSwipe(viewHolder)
//                        itemTouchHelper.startDrag(viewHolder)
                    }
                    itemTouchHelper.attachToRecyclerView(it)
                }

            }
            (it.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            (it.itemAnimator as SimpleItemAnimator) .removeDuration = 0
        }
    }
}