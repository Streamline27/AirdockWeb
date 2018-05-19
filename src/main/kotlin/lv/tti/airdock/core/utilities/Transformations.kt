package lv.tti.airdock.core.utilities

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.domain.WorkOrder
import lv.tti.airdock.rest.dto.LargeTaskDto
import lv.tti.airdock.rest.dto.TaskStatusDto
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
	Task.Status.TODO		 -> TaskStatusDto.TODO
	Task.Status.IN_PROGRESS -> TaskStatusDto.IN_PROGRESS
	Task.Status.DONE 		-> TaskStatusDto.DONE
	Task.Status.CANCELED	-> TaskStatusDto.CANCELED
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
	else -> throw IllegalArgumentException("Unmapped TaskStatusDto")
}




fun <FROM, TO>FROM?.transform(callback: (FROM) -> TO) : TO? = if (this != null) callback(this) else null