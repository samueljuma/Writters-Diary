package com.devjay.writtersdiary.adpters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.utils.formatCompleteTasks
import com.devjay.writtersdiary.utils.formatPendingTasksText

@BindingAdapter("writersName")
fun TextView.setWriterName(item: Writer?){
    item?.let {
        text = item.name
    }
}
@BindingAdapter("clientsName")
fun TextView.setClientName(item: Client?){
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

@BindingAdapter("clientsPendingTasks")
fun TextView.setClientsPendingTasks(item: Client?){
    item?.let {
        text = formatPendingTasksText(item.pendingTasks)
    }
}
@BindingAdapter("clientsCompletedTasks")
fun TextView.setClientsCompletedTasks(item: Client?){
    item?.let {
        text = formatCompleteTasks(item.completedTasks)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}