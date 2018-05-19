package lv.tti.airdock.rest

import lv.tti.airdock.core.services.TaskStatusService
import lv.tti.airdock.core.utilities.fromDto
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.core.utilities.toLargeDto
import lv.tti.airdock.rest.dto.TaskStatusDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskStatusController {

    @Autowired lateinit var taskStatusService: TaskStatusService

    @PutMapping("/task/{id}/status/{status}")
    fun setStatus(
            @PathVariable("id")     id        : Long,
            @PathVariable("status") statusDto : TaskStatusDto
    ) =
        taskStatusService.updateTaskStatus(
                id = id,
                status = statusDto.fromDto()
        ).toDto()
}