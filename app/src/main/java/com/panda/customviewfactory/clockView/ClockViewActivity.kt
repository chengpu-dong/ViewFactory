package com.panda.customviewfactory.clockView

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.panda.customviewfactory.R

/**
 * @ClassName ClockViewActivity
 * @Description TODO
 * @Author dongchengpu
 * @Date 2021/2/1 下午6:39
 * @Version 1.0
 */
class ClockViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock_view)
        Log.d("ClockViewActivity", "onCreate: ")
    }
}