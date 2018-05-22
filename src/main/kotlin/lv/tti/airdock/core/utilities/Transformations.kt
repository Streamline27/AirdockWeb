package lv.tti.airdock.core.utilities

import lv.tti.airdock.core.domain.Request
import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.core.services.ServiceKeeper.userService
import lv.tti.airdock.core.services.ServiceKeeper.workOrderService
import lv.tti.airdock.rest.dto.*
import java.util.*




fun TaskDto.fromDto(id: Long? = null) = Task(
		id = id,
		title = this.title,
		description = this.description,
		startDate = this.from,
		endDate = this.to,
		creationDate = this.created,
		user = this.assignee.transform(userService::getById),
		workOrder = this.workOrder.transform(workOrderService::getById)
)

fun RequestDto.fromDto(id: Long? = null) = Request(
		id = id,
		title = this.title,
		description = this.description,
		author = this.author.transform(userService::getById),
		creationDate = this.created ?: Date(),
		status = this.status.transform(Request.Status::valueOf) ?: Request.Status.DRAFT
)

fun WorkOrderDto.fromDto(id: Long? = null) = WorkOrder(
		id = id,
		title = this.title,
		description = this.description
)


/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from domain to dto objects
 */


fun Task.toLargeDto() = TaskLargeDto(
		id = this.id.toString(),
		title = this.title,
		description = this.description,
		from = this.startDate,
		to = this.endDate,
		created = this.creationDate,
		assignee = this.user.transform(User::toDto),
		workOrder = this.workOrder.transform(WorkOrder::toDto),
		status = this.status.toDto()
)

fun Task.toDto() = TaskDto(
		id = this.id.toString(),
		title = this.title,
		description = this.description,
		from = this.startDate,
		to = this.endDate,
		created = this.creationDate,
		assignee = this.user?.id,
		workOrder = this.workOrder?.id,
		status = this.status.toString()
)

fun User.toDto() = UserDto(
		id = this.id.toString(),
		name = this.name
)

fun WorkOrder.toDto() = WorkOrderDto(
		id = this.id.toString(),
		title = this.title,
		description = this.description
)

fun Request.toDto() = RequestDto(
		id = this.id.toString(),
		title = this.title,
		description = this.description,
		author = this.author?.id,
		created = this.creationDate,
		status = this.status.toString()
)

fun Request.toLargeDto() = RequestLargeDto(
		id = this.id.toString(),
		title = this.title,
		description = this.description,
		author = this.author.transform(User::toDto),
		created = this.creationDate,
		status = this.status.toString()
)
fun Task.Status.toDto() = when(this) {
	Task.Status.TODO		 -> TaskStatusDto.TODO
	Task.Status.IN_PROGRESS -> TaskStatusDto.IN_PROGRESS
	Task.Status.DONE 		-> TaskStatusDto.DONE
	Task.Status.CANCELED	-> TaskStatusDto.CANCELED
	Task.Status.SUSPENDED   -> TaskStatusDto.SUSPENDED
	else -> throw IllegalArgumentException("Unmapped Task.Status")
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from dto to domain objects
 */


fun TaskStatusDto.fromDto() = when(this) {
	TaskStatusDto.TODO		  -> Task.Status.TODO
	TaskStatusDto.IN_PROGRESS -> Task.Status.IN_PROGRESS
	TaskStatusDto.DONE 		  -> Task.Status.DONE
	TaskStatusDto.CANCELED	  -> Task.Status.CANCELED
	TaskStatusDto.SUSPENDED   -> Task.Status.SUSPENDED
	else -> throw IllegalArgumentException("Unmapped TaskStatusDto")
}




fun <FROM, TO> FROM?.transform(callback: (FROM) -> TO) = if (this != null) callback(this) else null