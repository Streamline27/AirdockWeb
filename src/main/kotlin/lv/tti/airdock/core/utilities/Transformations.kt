package lv.tti.airdock.core.utilities

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.rest.dto.LargeTaskDto
import lv.tti.airdock.rest.dto.UserDto
import lv.tti.airdock.rest.dto.WorkOrderDto


/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from dto to domain objects
 */


/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from domain to dto objects
 */

fun Task.toLargeDto() =	LargeTaskDto(
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

fun User.toDto() = UserDto(
	id = this.id,
	name = this.name
)

fun WorkOrder.toDto() = WorkOrderDto(
	id = this.id,
	title = this.title,
	description = this.description
)



fun <FROM, TO>FROM?.transform(callback: (FROM) -> TO) = if (this != null) callback(this) else null