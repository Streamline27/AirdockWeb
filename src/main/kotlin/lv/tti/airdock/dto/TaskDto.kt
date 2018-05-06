package lv.tti.airdock.dto

import java.util.*
import javax.validation.constraints.NotBlank

class TaskDto(
		@get:NotBlank
		var title: String = "",

		var description: String,
		var from: Date?,
		var to: Date?,
		var assignee: Long?) {

	constructor() : this("", "", null, null, null)
}