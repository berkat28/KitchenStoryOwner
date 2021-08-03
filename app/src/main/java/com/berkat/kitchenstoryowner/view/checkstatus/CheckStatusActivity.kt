package com.berkat.kitchenstoryowner.view.checkstatus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.berkat.kitchenstoryowner.R

class CheckStatusActivity : AppCompatActivity() {


    private lateinit var viewModel: CheckStatusViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_status)
    }

}