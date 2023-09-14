package com.example.biatechtestapp.presentation.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.biatechtestapp.databinding.TaskCurrentItemBinding
import com.example.biatechtestapp.databinding.TaskDefaultItemBinding
import com.example.biatechtestapp.databinding.TaskDoneItemBinding

class TasksAdapter: RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var tasks:List<TaskItemUi> = emptyList()

    fun setList(list: List<TaskItemUi>) {
        val diffUtilCallback = Callback(tasks, list)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        tasks = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TaskViewHolder {
        return when(viewType){
            DONE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = TaskDoneItemBinding.inflate(inflater,parent,false)
                TaskViewHolder.Done(binding)
            }
            CURRENT ->{
                val inflater = LayoutInflater.from(parent.context)
                val binding = TaskCurrentItemBinding.inflate(inflater,parent,false)
                TaskViewHolder.Current(binding)
            }
            DEFAULT ->{
                val inflater = LayoutInflater.from(parent.context)
                val binding = TaskDefaultItemBinding.inflate(inflater,parent,false)
                TaskViewHolder.Default(binding)
            }
            else-> throw IllegalStateException("Unprocessed viewType $viewType")

        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    override fun getItemViewType(position: Int): Int {
        return when(tasks[position]){
            is TaskItemUi.Done -> DONE
            is TaskItemUi.Current -> CURRENT
            else -> DEFAULT
        }
    }

    abstract class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){

        open fun bind(task: TaskItemUi) = Unit

        class Done(private val binding: TaskDoneItemBinding): TaskViewHolder(binding.root){
            override fun bind(task: TaskItemUi) {
                with(binding){
                    productTV.text = (task as TaskItemUi.Done).typeProduct
                    dateTV.text = task.date
                    addressFromTV.text = task.addressFrom
                    addressToTV.text = task.addressTo
                }

            }
        }

        class Current(private val binding: TaskCurrentItemBinding): TaskViewHolder(binding.root){
            override fun bind(task: TaskItemUi) {
                with(binding){
                    productTV.text = (task as TaskItemUi.Current).typeProduct
                    dateTV.text = task.date
                    addressFromTV.text = task.addressFrom
                    addressToTV.text = task.addressTo
                    details.text = task.details
                    parameters.text = task.parameters
                }
            }
        }

        class Default(private val binding: TaskDefaultItemBinding): TaskViewHolder(binding.root){
            override fun bind(task: TaskItemUi) {
                with(binding){
                    productTV.text = (task as TaskItemUi.Default).typeProduct
                    dateTV.text = task.date
                    addressFromTV.text = task.addressFrom
                    addressToTV.text = task.addressTo
                    details.text = task.details
                    parameters.text = task.parameters
                }
            }
        }

    }

    companion object{

        const val DONE = 0
        const val CURRENT = 1
        const val DEFAULT = 2


    }

    private class Callback(
        private val oldList: List<TaskItemUi>,
        private val newList: List<TaskItemUi>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldTask = oldList[oldItemPosition]
            val newTask = newList[newItemPosition]
            return oldTask.coreId == newTask.coreId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldTask = oldList[oldItemPosition]
            val newTask = newList[newItemPosition]
            return oldTask == newTask
        }
    }


}