package com.devjay.writtersdiary.utils

fun setIsPaidText(value: Boolean): String{
    return if(value){
        "Paid"
    }else {
        "Not Paid"
    }
}

fun setIsCompleteText(value: Boolean): String{
    return if(value){
        "Complete"
    }else {
        "Pending"
    }
}
