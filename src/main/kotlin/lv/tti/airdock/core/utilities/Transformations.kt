package lv.tti.airdock.core.utilities

import lv.tti.airdock.core.domain.Request
import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.core.services.ServiceKeeper.userService
import lv.tti.airdock.core.services.ServiceKeeper.workOrderService
import lv.tti.airdock.rest.dto.*
import java.util.*


/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from dto to domain objects
 */

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
		status = this.status.transform(Request.Status::valueOf) ?: Request.Status.PENDING
)

fun WorkOrderDto.fromDto(id: Long? = null) = WorkOrder(
		id = id,
		title = this.title,
		description = this.description
)


/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from domain to dto objects
 */


fun Task.toLargeDto() = LargeTaskDto(
		id = this.id,
		title = this.title,
		description = this.description,
		from = this.startDate,
		to = this.endDate,
		created = this.creationDate,
		assignee = this.user.transform(User::toDto),
		workOrder = this.workOrder.transform(WorkOrder::toDto),
		status = this.status.toString()
)

fun Task.toDto() = TaskDto(
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
		id = this.id,
		name = this.name
)

fun WorkOrder.toDto() = WorkOrderDto(
		id = this.id,
		title = this.title,
		description = this.description
)

fun Request.toDto() = RequestDto(
		title = this.title,
		description = this.description,
		author = this.author?.id,
		created = this.creationDate,
		status = this.status.toString()
)

fun Request.toLargeDto() = LargeRequestDto(
		id = this.id,
		title = this.title,
		description = this.description,
		author = this.author.transform(User::toDto),
		created = this.creationDate,
		status = this.status.toString()
)


fun <FROM, TO> FROM?.transform(callback: (FROM) -> TO) = if (this != null) callback(this) else null