package com.devjay.writtersdiary.utils

import android.annotation.SuppressLint
import android.text.Editable
import java.text.SimpleDateFormat
import java.util.function.DoubleBinaryOperator

fun formatPendingTasksText(value: Int): String{
    return "Pending (${value})"
}
fun formatCompleteTasks(value: Int):String{
    return "Completed (${value})"
}

//formats date to string; 08/13/2021
@SuppressLint("SimpleDateFormat")
fun formatToDateString(systemTime: Long): String {
    return SimpleDateFormat("MM/dd/yyyy")
        .format(systemTime).toString()
}

// formats time to string; 12:00
@SuppressLint("SimpleDateFormat")
fun formatToTimeString(systemTime: Long): String {
    return SimpleDateFormat("HH:mm")
        .format(systemTime).toString()
}

// formats amount payable to string; $ 20.00
fun formatAmountPayable(value: Double): String{
    return "$ $value"
}

// Format order number; Order No: 12345
fun formatOrderNumber(value: String): String{
    return "Order No: $value"
}
