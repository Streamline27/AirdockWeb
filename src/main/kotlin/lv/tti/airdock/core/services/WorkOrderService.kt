package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.WorkOrderRepository
import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.rest.dto.WorkOrderDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WorkOrderService {

	@Autowired
	lateinit var repository: WorkOrderRepository

	fun getWorkOrders(): List<WorkOrderDto> = repository.findAll().map(WorkOrder::toDto)

	fun deleteWorkOrder(id: Long) = repository.deleteById(id)

	fun getById(id: Long) = repository.findById(id).get()

	fun saveWorkOrder(workOrder: WorkOrder) = repository.save(workOrder)

}