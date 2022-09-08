package com.naulepilef.todo.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naulepilef.todo.R
import com.naulepilef.todo.adapter.TaskAdapter
import com.naulepilef.todo.databinding.ActivityMainBinding
import com.naulepilef.todo.helper.RecyclerItemClickListener
import com.naulepilef.todo.helper.RecyclerItemClickListener.OnItemClickListener
import com.naulepilef.todo.helper.TaskDAO
import com.naulepilef.todo.helper.ToastHelper
import com.naulepilef.todo.model.Task

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewTaskList: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private var taskList: MutableList<Task> = arrayListOf()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewTaskList = findViewById(R.id.content_main_recycler_taskList)

        setSupportActionBar(binding.toolbar)

        recyclerViewTaskList.addOnItemTouchListener(
            RecyclerItemClickListener(
                applicationContext,
                recyclerViewTaskList,
                object: OnItemClickListener{
                    override fun onItemClick(view: View?, position: Int) {
                        val task = taskList[position]

                        val intent = Intent(applicationContext, AddTaskActivity::class.java)
                        intent.putExtra("task", task)

                        startActivity(intent)
                    }

                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        TODO("Not yet implemented")
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        val dialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

                        val task: Task = taskList[position]

                        dialog.setTitle(getString(R.string.alertDialog_confirmDelete_title))
                        dialog.setMessage(getString(R.string.alertDialog_confirmDelete_body, task.description))

                        dialog.setPositiveButton(
                            android.R.string.ok
                        ) { dialogInterface, i ->
                            val taskDao = TaskDAO(applicationContext)

                            if (taskDao.delete(task)) {
                                loadTaskList()
                                ToastHelper.DeleteSuccess(applicationContext)
                            }else{
                                ToastHelper.DeleteFail(applicationContext)
                            }
                        }
                        dialog.setNegativeButton(android.R.string.cancel, null)

                        dialog.create()
                        dialog.show()
                    }
                }
            )
        )

        binding.fab.setOnClickListener {
            val intent = Intent(applicationContext, AddTaskActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        loadTaskList()
    }

    private fun loadTaskList(){
        val taskDao = TaskDAO(applicationContext)
        taskList = taskDao.list()

        taskAdapter = TaskAdapter(taskList)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewTaskList.layoutManager = layoutManager
        recyclerViewTaskList.setHasFixedSize(true)
        recyclerViewTaskList.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))
        recyclerViewTaskList.adapter = taskAdapter
    }
}