package lv.tti.airdock.rest.dto

import java.util.*

class RequestDto(
		var title: String = "",
		var status: String? = null,
		var description: String = "",
		var created: Date? = null,
		var author: Long? = null
)