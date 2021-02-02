package com.panda.customviewfactory.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.panda.customviewfactory.R
import com.panda.customviewfactory.home.model.MainItemModel
import java.util.ArrayList

/**
 * @ClassName MainViewModel
 * @Description TODO
 * @Author dongchengpu
 * @Date 2021/1/29 下午4:57
 * @Version 1.0
 */
class MainViewModel() : ViewModel() {
    private val typeData = MutableLiveData<List<MainItemModel>>()

    fun getTypeData(): MutableLiveData<List<MainItemModel>> {
        return typeData
    }

    fun initData(){
        val itemList = ArrayList<MainItemModel>()
        itemList.add(buildItem(R.raw.clock_view, "ClockView"))
        itemList.add(buildItem(R.drawable.ic_launcher_foreground, "BubbleTextView"))
        typeData.value = itemList
    }

    private fun buildItem(icon: Int, title: String): MainItemModel {
        val item = MainItemModel()
        item.iconRes = icon
        item.title = title
        return item
    }
}