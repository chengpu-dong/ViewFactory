package com.panda.customviewfactory.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.panda.customviewfactory.R
import com.panda.customviewfactory.bubbleTextView.BubbleTextViewActivity
import com.panda.customviewfactory.clockView.ClockView
import com.panda.customviewfactory.clockView.ClockViewActivity
import com.panda.customviewfactory.home.model.MainItemModel
import com.panda.customviewfactory.home.view.adapter.MainAdapter
import com.panda.customviewfactory.home.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainAdapter.ItemClickCallback {
    private var viewModel: MainViewModel? = null
    private var adapter: MainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initView()
        initViewModel()
        initData()
    }

    private fun initView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager: LinearLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        adapter = MainAdapter(this)
        recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel!!.getTypeData()
            .observe(
                this,
                Observer { list: List<MainItemModel> ->
                    adapter?.items = ArrayList<MainItemModel>(list)
                })
    }

    private fun initData() {
        viewModel?.initData()
    }

    override fun onItemClicked(item: MainItemModel) {
        val title: String = item.title
        if (title == "ClockView") {
            startActivity(Intent(this, ClockViewActivity::class.java))
        } else if (title == "BubbleTextView") {
            startActivity(Intent(this, BubbleTextViewActivity::class.java))
        }
    }
}