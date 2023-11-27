package com.example.capstone

data class LocalShopArray(
    val shopName : String = "",
    val shopDescription : String = "",
    val shopLocation : String = ""
){
    constructor(map: Map<String, Any>) : this(
        map["shopName"] as String,
        map["shopDescription"] as String,
        map["shopLocation"] as String
    )

}