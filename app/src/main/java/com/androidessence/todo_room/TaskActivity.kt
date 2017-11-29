package com.androidessence.todo_room

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter()
        task_list.adapter = adapter
        task_list.layoutManager = layoutManager

        taskDao().getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ adapter.tasks = it })

        fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}
