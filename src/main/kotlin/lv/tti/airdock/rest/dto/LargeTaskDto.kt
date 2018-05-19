package lv.tti.airdock.rest.dto

import java.util.*

class LargeTaskDto(
		var id: String? = null,
		var title: String = "",

		var description: String = "",
		var from: Date? = null,
		var to: Date? = null,
		var created: Date? = null,
		var assignee: UserDto? = null,
		var workOrder: WorkOrderDto? = null,
		var status : TaskStatusDto = TaskStatusDto.UNDEFINED
)