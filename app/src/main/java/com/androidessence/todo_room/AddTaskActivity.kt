package com.androidessence.todo_room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_task.*


class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        submit.setOnClickListener {
            val description = task_description.text.toString()

            if (!description.isEmpty()) {
                val task = Task(description)

                Single.fromCallable { taskDao().insertAll(task) }
                        .subscribeOn(Schedulers.newThread())
                        .subscribe()

                finish()
            }
        }
    }
}
