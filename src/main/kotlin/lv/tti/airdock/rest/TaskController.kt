package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.services.TaskService
import lv.tti.airdock.core.services.UserService
import lv.tti.airdock.core.services.WorkOrderService
import lv.tti.airdock.core.utilities.toLargeDto
import lv.tti.airdock.core.utilities.transform
import lv.tti.airdock.rest.dto.LargeTaskDto
import lv.tti.airdock.rest.dto.TaskDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskController {

    @Autowired lateinit var taskService: TaskService
    @Autowired lateinit var userService: UserService
    @Autowired lateinit var workOrderService: WorkOrderService

    @GetMapping("/task/{id}")
    fun getTask(@PathVariable("id") id : Long) = taskService.getTaskById(id).toLargeDto()

	@GetMapping("/tasks")
    fun getTasks() = taskService.getAllTasks().map(Task::toLargeDto)

	@GetMapping("/tasks/search")
	fun searchTasks(@RequestParam filter: Map<String,String>?): List<LargeTaskDto> {
		if (filter == null || filter.isEmpty()) {
			return taskService.getAllTasks().map(Task::toLargeDto)
		}
		else {
			return taskService.searchTasks(filter).map(Task::toLargeDto)
		}
	}

    @PostMapping("/task")
	fun saveTask(@RequestBody task: TaskDto) {
		taskService.saveTask(task.fromDto())
	}

	@PutMapping("task/{id}")
	fun updateTask(@PathVariable id: Long, @RequestBody task: TaskDto) {
		taskService.saveTask(task.fromDto(id))
	}

	@DeleteMapping("task/{id}")
	fun updateTask(@PathVariable id: Long) {
		taskService.deleteTask(id)
	}

	private fun TaskDto.fromDto(id: Long? = null) = Task(
			id = id,
			title = this.title,
			description = this.description,
			startDate = this.from,
			endDate = this.to,
			creationDate = this.created,
			user = this.assignee.transform(userService::getById),
			workOrder = this.workOrder.transform(workOrderService::getById)
	)

}