package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.dto.TaskDto
import lv.tti.airdock.core.services.WorkOrderService
import lv.tti.airdock.dto.WorkOrderDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/workorders")
class WorkOrderController {

    @Autowired lateinit var workOrderService: WorkOrderService

	@GetMapping()
    fun getWorkOrders() = workOrderService.getWorkOrders()

	@GetMapping("/{id}")
	fun getWorkOrder(@PathVariable("id") id : Long) = workOrderService.getById(id)

	@DeleteMapping("/{id}")
	fun updateTask(@PathVariable id: Long) {
		workOrderService.deleteWorkOrder(id)
	}

	@PutMapping("/{id}")
	fun updateTask(@PathVariable id: Long, @RequestBody workOrder: WorkOrderDto) {
		workOrderService.saveWorkOrder(
				WorkOrder(
						id = id,
						title = workOrder.title,
						description = workOrder.description
				)
		)
	}

	@PostMapping("/workorder")
	fun saveTask(@RequestBody task: TaskDto) {
		workOrderService.saveWorkOrder(
				WorkOrder(
						title = task.title,
						description = task.description
				)
		)
	}

}