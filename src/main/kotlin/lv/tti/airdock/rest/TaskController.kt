package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.dto.TaskDto
import lv.tti.airdock.core.services.TaskService
import lv.tti.airdock.core.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController {

    @Autowired lateinit var taskService: TaskService
    @Autowired lateinit var userService: UserService

    @GetMapping("/{id}")
    fun getTask(@PathVariable("id") id : Long) = taskService.getTaskById(id)

	@GetMapping()
    fun getTasks() = taskService.getAllTasks()

    @PostMapping("/task")
	fun saveTask(@RequestBody task: TaskDto) {
		taskService.saveTask(
				Task(
					title = task.title,
					description = task.description,
					start = task.from,
					end = task.to,
					user = if (task.assignee != null) userService.getById(task.assignee!!) else null
				)
		)
	}

	@PutMapping("/{id}")
	fun updateTask(@PathVariable id: Long, @RequestBody task: TaskDto) {
		taskService.saveTask(
				Task(
						id = id,
						title = task.title,
						description = task.description,
						start = task.from,
						end = task.to,
						user = if (task.assignee != null) userService.getById(task.assignee!!) else null
				)
		)
	}

	@DeleteMapping("/{id}")
	fun updateTask(@PathVariable id: Long) {
		taskService.deleteTask(id)
	}
}