package lv.tti.airdock.rest

import lv.tti.airdock.core.services.ServiceKeeper.workOrderService
import lv.tti.airdock.core.utilities.fromDto
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.rest.dto.WorkOrderDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/workorders")
class WorkOrderController {

	@GetMapping()
    fun getWorkOrders() = workOrderService.getWorkOrders()

	@GetMapping("{id}")
	fun getWorkOrder(@PathVariable("id") id : Long) = workOrderService.getById(id).toDto()

	@DeleteMapping("{id}")
	fun updateTask(@PathVariable id: Long) {
		workOrderService.deleteWorkOrder(id)
	}

	@PutMapping("workorder/{id}")
	fun updateTask(@PathVariable id: Long, @RequestBody workOrder: WorkOrderDto) {
		workOrderService.saveWorkOrder(workOrder.fromDto(id))
	}

	@PostMapping("workorder")
	fun saveWorkOrder(@RequestBody workOrder: WorkOrderDto) {
		workOrderService.saveWorkOrder(workOrder.fromDto())
	}

}