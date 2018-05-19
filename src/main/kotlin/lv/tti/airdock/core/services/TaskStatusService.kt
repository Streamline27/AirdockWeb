package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.TaskRepository
import lv.tti.airdock.core.domain.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskStatusService {

    @Autowired lateinit var taskDao : TaskRepository

    fun updateTaskStatus(id: Long, status : Task.Status) {
        taskDao.updateTaskStatus(id, status)
    }
}