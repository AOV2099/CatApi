package com.aov2099.catapi.controller


import com.aov2099.catapi.model.CatResponse

interface CatsClickListener {
    fun onClick( cat: CatResponse)
}
