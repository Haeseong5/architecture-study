package com.example.architecture_study.mvp_pattern

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

public class BaseActivity : AppCompatActivity(){

    val mProgressDialog = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}