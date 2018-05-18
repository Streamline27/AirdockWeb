package lv.tti.airdock.rest

import lv.tti.airdock.core.services.ServiceKeeper.registrationService
import lv.tti.airdock.core.services.ServiceKeeper.userService
import lv.tti.airdock.core.utilities.toDto
import lv.tti.airdock.rest.dto.RegistrationDto
import lv.tti.airdock.rest.dto.UserDto
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/users")
class UserController {

	@GetMapping("workers")
	fun getWorkers() : List<UserDto> = userService.getWorkers().map { it.toDto() }

	@PostMapping("worker/register")
	fun register(@RequestBody @Valid userDto : RegistrationDto) : UserDto = registrationService.registerWorker(userDto).toDto()
}