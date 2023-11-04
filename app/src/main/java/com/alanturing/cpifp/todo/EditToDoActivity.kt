package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditToDoBinding
import com.alanturing.cpifp.todo.model.Task

class EditToDoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEditToDoBinding
    val repository = TaskLocalRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val task: Task? = intent?.extras?.getParcelable("TASK")
        var taskTitle = ""
        var taskDescription = ""
        var taskId = 0
        var taskCheck = false
        if(task != null){
            taskTitle = task.title.toString()
            taskDescription = task.description.toString()
            taskId = task.id
            taskCheck = task.isCompleted
        }

        binding.titleInput.setText(taskTitle)
        binding.descriptionInput.setText(taskDescription)

        binding.changeButton.setOnClickListener{
            val task = Task(taskId, binding.titleInput.text.toString(), binding.descriptionInput.text.toString(), binding.isCompleted.isChecked)
            repository.update(task)
            setResult(Activity.RESULT_OK)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.cancelButton.setOnClickListener{
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}