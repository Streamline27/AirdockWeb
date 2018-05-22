package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.Request
import lv.tti.airdock.core.services.ServiceKeeper.requestService
import lv.tti.airdock.core.utilities.fromDto
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.rest.dto.RequestDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class RequestController {

	@PostMapping("/request")
	fun saveRequest() =	requestService.createDraftRequest().toDto()

	@PutMapping("/request/{id}")
	fun updateRequest(@PathVariable id: Long, @RequestBody request: RequestDto) =
            requestService.updateRequestField(request.fromDto(id))
                    .toDto()

	@DeleteMapping("/request/{id}")
	fun deleteRequest(@PathVariable id: Long) = requestService.deleteRequest(id)

	@GetMapping("/worker/{id}/requests")
	fun getUserRequests(@PathVariable id: Long) = requestService.getRequestsByUserId(id).map { it.toDto() }


}