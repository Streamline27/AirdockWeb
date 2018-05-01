package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.TaskRepository
import lv.tti.airdock.core.domain.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {

    @Autowired lateinit var taskDao : TaskRepository

    fun getTaskById(id : Long) = taskDao.findById(id).get()

    fun getAllTasks() = taskDao.findAll()

    fun saveTask(task: Task) = taskDao.save(task)
}