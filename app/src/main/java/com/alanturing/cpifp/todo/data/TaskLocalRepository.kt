package com.alanturing.cpifp.todo.data

import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    companion object {
        private var _INSTANCE:TaskLocalRepository? = null
        fun getInstance():TaskLocalRepository {
            if(_INSTANCE == null){
                _INSTANCE = TaskLocalRepository()
            }
            return _INSTANCE!!
        }
    }

    private var contador = 0
    private val _tasks = mutableListOf<Task>()
    init {
        _tasks.add(Task(1,"Comprar leche","Leche desnatada",false))
        _tasks.add(Task(2,"Hacer práctica Android","Completar todos los TODOS",false))
    }


    val tasks:List<Task>
        get() = _tasks

    fun add(task:Task) {
        _tasks.add(task)
    }
    fun delete(id:Int) {

    }
    fun update(task:Task) {
        val createdTask= _tasks.find{ it.id == task.id }
        if(createdTask != null)
            _tasks[_tasks.indexOf(createdTask)] = task
    }

    fun getNextTaksId(): Int{
        return ++contador
    }
}