package com.example.databindingtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingtest.databinding.GoodsBinding

class Main2Activity : AppCompatActivity() {
    private lateinit var goods: Goods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val goodsBinding: GoodsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main2)
        goods = Goods()
        goods.name="变形金刚"
        goods.details = "G1B23345"
        goods.price="18"
        goodsBinding.goods = goods
        goodsBinding.goodHandler = GoodsHandler()
    }

    inner class GoodsHandler {
        fun setGoodsName() {
            goods.name="金刚葫芦娃"
        }

        fun setGoodsDetails() {
            goods.details="GV123-BBV"
        }

        fun setGoodsPrice(){
            goods.price="33"
        }

        fun setAllData(){
            goods.name="高达"
            goods.details="GD-13345"
            goods.price="55"
        }
    }
}

