package lv.tti.airdock.dto

import java.util.*

class TaskDto(var title: String, var description: String, var from: Date?, var to: Date?, var assignee: Long?) {
	constructor() : this("", "", null, null, null)
}