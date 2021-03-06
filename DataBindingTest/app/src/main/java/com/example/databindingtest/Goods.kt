package com.example.databindingtest

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
class Goods : BaseObservable() {
    @Bindable
    var name: String? = ""
        set(value) {
            field=value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var details: String? = ""
        set(value) {
            field=value
            notifyPropertyChanged(BR.details)
        }
    @Bindable
    var price: String? = ""
        set(value) {
            field=value
            notifyPropertyChanged(BR.price)
        }




}