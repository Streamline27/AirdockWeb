package lv.tti.airdock.core.utilities

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.rest.dto.LargeTaskDto
import lv.tti.airdock.rest.dto.StatusDto
import lv.tti.airdock.rest.dto.UserDto
import lv.tti.airdock.rest.dto.WorkOrderDto





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
	assignee = this.user?.toDto(),
	workOrder = this.workOrder.transform(WorkOrder::toDto),
	status = this.status.toDto()
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

fun Task.Status.toDto() = when(this) {
	Task.Status.TODO		 -> StatusDto.TODO
	Task.Status.IN_PROGRESS -> StatusDto.IN_PROGRESS
	Task.Status.DONE 		-> StatusDto.DONE
	Task.Status.CANCELED	-> StatusDto.CANCELED
	else -> throw IllegalArgumentException("Unmapped Task.Status")
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from dto to domain objects
 */


fun StatusDto.fromDto() = when(this) {
	StatusDto.TODO		  -> Task.Status.TODO
	StatusDto.IN_PROGRESS -> Task.Status.IN_PROGRESS
	StatusDto.DONE 		  -> Task.Status.DONE
	StatusDto.CANCELED	  -> Task.Status.CANCELED
	else -> throw IllegalArgumentException("Unmapped StatusDto")
}




fun <FROM, TO>FROM?.transform(callback: (FROM) -> TO) : TO? = if (this != null) callback(this) else null