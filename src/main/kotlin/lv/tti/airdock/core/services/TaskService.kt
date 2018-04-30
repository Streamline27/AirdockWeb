package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {

    @Autowired lateinit var taskDao : TaskRepository;

    fun getTaskById(id : Long) = taskDao.findById(id).get()
}