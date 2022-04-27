package com.example.viewbindingrv

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewbindingrv.databinding.ActivityMainBinding
import com.example.viewbindingrv.databinding.CustomDialogFragmentBinding

class MainActivity : AppCompatActivity() {


    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = MainAdapter(TaskList.taskList)
        binding?.taskRV?.adapter = adapter







        binding?.addTaskBtn?.setOnClickListener {

            val dialog = Dialog(this)
            val customDialogLayoutBinding:CustomDialogFragmentBinding = CustomDialogFragmentBinding.inflate(layoutInflater)
            dialog.setContentView(customDialogLayoutBinding.root)

            customDialogLayoutBinding.cancelTaskBtn.setOnClickListener {
                dialog.dismiss()
            }

            customDialogLayoutBinding.createTaskBtn.setOnClickListener {
                var taskText = customDialogLayoutBinding.createTaskET.text.toString()
                var taskTime = customDialogLayoutBinding.createTaskTimeET.text.toString()

                if(!taskText.isEmpty() && taskTime.length>3){
                    TaskList.taskList.add(Task(taskText, taskTime))
                    TaskList.taskList.sortWith(compareBy { it.timeStamp })
                    binding?.taskRV?.adapter?.notifyDataSetChanged()

                    if(binding?.remTaskBtn?.isClickable == false){
                        binding?.remTaskBtn?.isClickable = true
                    }

                }
                dialog.dismiss()
            }

            dialog.show()
        }






        binding?.remTaskBtn?.setOnClickListener {
            if(TaskList.taskList.size>0) {
                TaskList.taskList.removeAt(0)
                println(TaskList.taskList.size.toString())
                binding?.taskRV?.adapter?.notifyDataSetChanged()
            }
            if(TaskList.taskList.size==0) {
                binding?.remTaskBtn?.isClickable = false
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}