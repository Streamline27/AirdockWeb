package lv.tti.airdock.core.dto

import java.util.*

class TaskDto(var title: String, var description: String, var from: Date, var to: Date, var assignee: Long?) {
	constructor() : this("", "", Date(), Date(), null)
}