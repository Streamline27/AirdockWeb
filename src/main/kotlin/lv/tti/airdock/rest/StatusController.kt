package lv.tti.airdock.rest

import lv.tti.airdock.core.services.StatusService
import lv.tti.airdock.core.utilities.fromDto
import lv.tti.airdock.core.utilities.transform
import lv.tti.airdock.rest.dto.StatusDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Service

@RestController
@RequestMapping("/api")
class StatusController {

    @Autowired lateinit var statusService: StatusService

    @PutMapping("/task/{id}/status")
    fun setStatus(
            @RequestParam("id") id : Long,
            @RequestBody statusDto: StatusDto
    ) {
        statusService.updateTaskStatus(
                id = id,
                status = statusDto.fromDto()
        )
    }

}