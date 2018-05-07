package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.TaskRepository
import lv.tti.airdock.core.domain.Task
import org.apache.tomcat.jni.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specifications.where
import org.springframework.stereotype.Service

@Service
class TaskService {

    @Autowired lateinit var taskDao : TaskRepository

    fun getTaskById(id : Long) = taskDao.findById(id).get()

    fun getAllTasks(): List<Task> = taskDao.findAll()

    fun searchTasks(filter: Map<String, String>): List<Task> {
        if (filter["name"].isNullOrBlank())
            return taskDao.findAll()
        return taskDao.search("%" + filter["name"] + "%")
    }

    fun saveTask(task: Task) = taskDao.save(task)

    fun deleteTask(id: Long) = taskDao.deleteById(id)
}