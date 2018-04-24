package lv.tti.airdock.core.utilities

import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.dto.TaskDto

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from dto to domain objects
 */
fun TaskDto.toDomain() = Task(this.id ?: 0, this.description ?: "")


/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Transformations from domain to dto objects
 */
fun Task.toDto() = TaskDto(this.id, this.description)