package com.example.customview.volumetwo.four

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.customview.R
import com.example.customview.databinding.ActivitySolveScrollConflictBinding

class SolveScrollConflictActivity : AppCompatActivity() {

    private lateinit var activitySolveScrollConflictBinding: ActivitySolveScrollConflictBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySolveScrollConflictBinding = ActivitySolveScrollConflictBinding.inflate(layoutInflater)
        setContentView(activitySolveScrollConflictBinding.root)
        activitySolveScrollConflictBinding.tv.movementMethod = ScrollingMovementMethod.getInstance()
    }
}