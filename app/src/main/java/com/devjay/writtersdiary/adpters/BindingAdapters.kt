package com.devjay.writtersdiary.adpters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.utils.*

/**
 * WRITER PROPERTIES
 */
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
/**
 *
 * CLIENT PROPERTIES
 */
@BindingAdapter("clientsName")
fun TextView.setClientName(item: Client?){
    item?.let {
        text = item.name
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
/**
 *
 * WRITER_TASK PROPERTIES
 */
@BindingAdapter("writerTaskTitle")
fun TextView.setWriterTaskTitle(task: WriterTask?){
    task?.let {
        text = task.title
    }
}
@BindingAdapter("writerTaskOrderNo")
fun TextView.setWriterTaskOrderNo(task: WriterTask?){
    task?.let {
        text = formatOrderNumber(task.orderNumber)
    }
}
@BindingAdapter("writerTaskPageCount")
fun TextView.setWriterTaskPageCount(task: WriterTask?){
    task?.let {
        text = task.pageCount.toString()
    }
}
@BindingAdapter("writerTaskWordCount")
fun TextView.setWriterTaskWordCount(task: WriterTask?){
    task?.let {
        text = task.numberOfPagesOrWordCount.toString()
    }
}
@BindingAdapter("writerTaskDateCreated")
fun TextView.setWriterTaskDateCreated(task: WriterTask?){
    task?.let {
        text = formatToDateString(task.time_created)
    }
}
@BindingAdapter("writerTaskTimeCreated")
fun TextView.setWriterTaskTimeCreated(task: WriterTask?){
    task?.let {
        text = formatToTimeString(task.time_created)
    }
}
@BindingAdapter("writerTaskIsPaid")
fun TextView.setWriterTaskIsPaid(task: WriterTask?){
    task?.let {
        text = setIsPaidText(task.isPaid)
    }
}
@BindingAdapter("writerTaskIsComplete")
fun TextView.setWriterTaskIsComplete(task: WriterTask?){
    task?.let {
        text = setIsCompleteText(task.isComplete)
    }
}
@BindingAdapter("writerTaskAmountPayable")
fun TextView.setWriterTaskAmountPayable(task: WriterTask?){
    task?.let {
        text = formatAmountPayable(task.amountPayable)
    }
}

/**
 *
 * CLIENT_TASK PROPERTIES
 */

/**
 * OTHER BINDING ADAPTERS
 */
@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}