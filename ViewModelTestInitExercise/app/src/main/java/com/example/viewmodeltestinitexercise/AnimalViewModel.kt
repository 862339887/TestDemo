package com.example.viewmodeltestinitexercise

import androidx.annotation.InspectableProperty
import androidx.databinding.ObservableField
import com.example.viewmodeltestinitexercise.model.Animal

class AnimalViewModel(animal: Animal) {
    private var animal:Animal?=null
    init {
        this.animal=animal
    }
    val info= ObservableField<String>("${animal.name} 叫了 ${animal.shoutCount}声..")
    @InspectableProperty
    fun shout(){
        animal!!.shoutCount=animal!!.shoutCount!!+1
        info.set("${animal!!.name} 叫了 ${animal!!.shoutCount}声..")
    }
}