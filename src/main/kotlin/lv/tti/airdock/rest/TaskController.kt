package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.services.ServiceKeeper.taskService
import lv.tti.airdock.core.services.TaskService
import lv.tti.airdock.core.utilities.fromDto
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.core.utilities.toLargeDto
import lv.tti.airdock.rest.dto.LargeTaskDto
import lv.tti.airdock.rest.dto.TaskDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskController {

	@GetMapping("/tasks/search")
	fun searchTasks(
			@RequestParam filter: Map<String,String>?
	): List<LargeTaskDto> {
		if (filter == null || filter.isEmpty()) {
			return taskService.getAllTasks().map(Task::toLargeDto)
		}
		else {
			return taskService.searchTasks(filter).map(Task::toLargeDto)
		}
	}

    @GetMapping("/task/{id}")
    fun getTask(@PathVariable("id") id : Long) = taskService.getTaskById(id).toDto()

	@GetMapping("/tasks")
    fun getTasks() = taskService.getAllTasks().map(Task::toLargeDto)

	@GetMapping("/worker/{id}/tasks")
	fun getUserTasks(@PathVariable("id") id : Long) = taskService.getUserTasks(id).map { it.toDto() }

    @PostMapping("/task")
	fun saveTask(@RequestBody task: TaskDto) = taskService.saveTask(task.fromDto())

	@PutMapping("task/{id}")
	fun updateTask(@PathVariable id: Long, @RequestBody task: TaskDto) = taskService.saveTask(task.fromDto(id))

	@DeleteMapping("task/{id}")
	fun deleteTask(@PathVariable id: Long) = taskService.deleteTask(id)
}