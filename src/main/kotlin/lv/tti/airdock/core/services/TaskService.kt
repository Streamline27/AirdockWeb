package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.TaskRepository
import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.utilities.transform
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService {

    @Autowired lateinit var taskDao : TaskRepository

    fun getTaskById(id : Long) = taskDao.findById(id).get()

    fun getAllTasks(): List<Task> = taskDao.findAll()

    fun searchTasks(filter: Map<String, String>): List<Task> {
        return taskDao.search(filter["name"].orEmpty(), filter["workOrder"].orEmpty())
    }

    fun saveTask(task: Task) {
        task.creationDate = Date()
        task.status = Task.Status.TODO
        taskDao.save(task)
    }

    fun deleteTask(id: Long) = taskDao.deleteById(id)
}