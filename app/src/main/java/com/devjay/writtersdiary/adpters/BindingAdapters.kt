package com.devjay.writtersdiary.adpters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.utils.formatCompleteTasks
import com.devjay.writtersdiary.utils.formatPendingTasksText

@BindingAdapter("writersName")
fun TextView.setWriterName(item: Writer?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("writersPendingTasks")
fun TextView.setWritersPendingTasks(item: Writer?){
    item?.let {
        text = formatPendingTasksText(item.pendingTasks)
    }
}
@BindingAdapter("writersCompletedTasks")
fun TextView.setWritersCompletedTasks(item: Writer?){
    item?.let {
        text = formatCompleteTasks(item.completedTasks)
    }
}