package com.xuan.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eevMainTest.addOnClickListener(View.OnClickListener {
            Toast.makeText(this, "点击了空白页面", Toast.LENGTH_SHORT).show()
        })
    }
}
