package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.Request
import lv.tti.airdock.core.services.ServiceKeeper.requestService
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.core.utilities.toLargeDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class RequestStatusController {

	@GetMapping("requests")
	fun getRequests(@RequestParam("status") status: String?) = requestService.getRequestsBy(status).map { it.toLargeDto() }

	@PutMapping("request/{id}/status/{status}")
	fun updateRequestStatus(@PathVariable id: Long, @PathVariable status: String) =
			requestService.updateRequestStatus(id, Request.Status.valueOf(status)).toLargeDto()

	@GetMapping("request/statuses")
	fun getRequestStatuses() = Request.Status.values().map { it.toString() }

}