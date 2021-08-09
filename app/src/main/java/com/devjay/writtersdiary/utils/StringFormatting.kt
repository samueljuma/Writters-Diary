package com.devjay.writtersdiary.utils

fun formatWhoNumber(number:Int): String{
    return "(${number})";
}
fun formatPendingTasksText(value: Int): String{
    return "Pending (${value})"
}
fun formatCompleteTasks(value: Int):String{
    return "Completed (${value})"
}