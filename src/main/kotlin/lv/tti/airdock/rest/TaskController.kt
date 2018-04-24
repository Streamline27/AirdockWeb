package lv.tti.airdock.rest

import lv.tti.airdock.core.services.TaskService
import lv.tti.airdock.dto.TaskDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/tasks")
class TaskController {

    @Autowired lateinit var taskService: TaskService

    @GetMapping("/{id}")
    fun getTask(@PathVariable("id") id : Long) : TaskDto = taskService.getTaskById(id)

    @GetMapping
    fun getAllTasks() : List<TaskDto> = taskService.getAllTasks();

    @PostMapping
    fun saveTask(@RequestBody taskDto : TaskDto) : TaskDto = taskService.saveTask(taskDto)

}