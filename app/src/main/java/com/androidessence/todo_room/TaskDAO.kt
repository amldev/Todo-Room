package com.androidessence.todo_room

import android.arch.persistence.room.*

/**
 * Interface for retrieving Task info.
 */
@Dao
interface TaskDAO {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE completed = :arg0")
    fun getComplete(complete: Boolean): List<Task>

    @Insert
    fun insertAll(vararg tasks: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}