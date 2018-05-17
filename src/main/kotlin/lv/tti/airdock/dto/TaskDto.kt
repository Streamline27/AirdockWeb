package lv.tti.airdock.dto

import java.util.*
import javax.validation.constraints.NotBlank

class TaskDto(
		@get:NotBlank
		var title: String = "",

		var description: String = "",
		var from: Date? = null,
		var to: Date? = null,
		var created: Date? = null,
		var assignee: Long? = null,
		var workOrder: Long? = null
)