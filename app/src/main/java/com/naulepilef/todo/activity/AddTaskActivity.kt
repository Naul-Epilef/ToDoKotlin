package com.naulepilef.todo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.textfield.TextInputEditText
import com.naulepilef.todo.R
import com.naulepilef.todo.helper.TaskDAO
import com.naulepilef.todo.helper.ToastHelper
import com.naulepilef.todo.model.Task

class AddTaskActivity : AppCompatActivity() {
    private var task: Task? = null
    private lateinit var inputDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        title = getString(R.string.activity_add_task_actionBar_title)

        inputDescription = findViewById(R.id.activity_add_task_input_description)

        if (intent.getSerializableExtra("task") != null){
            task = intent.getSerializableExtra("task") as Task

            inputDescription.setText(task?.description)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_task, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_add_task_close -> finish()
            R.id.menu_add_task_save -> {
                val taskDao = TaskDAO(applicationContext)
                if (task == null){
                    val newTask = Task()
                    newTask.description = inputDescription.text.toString()

//                    DON'T USE:
//                    newTask.description.isNotEmpty()
//                    BECAUSE isNotEmpty DO NOT VALIDATE IF ENTER WAS PRESSED
                    if (newTask.description.trim().length > 0){
                        if (taskDao.save(newTask)){
                            ToastHelper.CreateSuccess(applicationContext)
                        }else{
                            ToastHelper.CreateFail(applicationContext)
                        }
                        finish()
                    }else {
                        ToastHelper.EmptyField(applicationContext)
                    }
                }else{
                    val updateTask = task as Task

                    updateTask.description = inputDescription.text.toString()

                    if (updateTask.description.trim().length > 0){
                        if (taskDao.update(updateTask)){
                            ToastHelper.UpgradeSuccess(applicationContext)
                        }else{
                            ToastHelper.UpgradeFail(applicationContext)
                        }
                        finish()
                    }else {
                        ToastHelper.EmptyField(applicationContext)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}