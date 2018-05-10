package lv.tti.airdock.rest

import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.services.RegistrationService
import lv.tti.airdock.core.services.SessionService
import lv.tti.airdock.core.services.UserService
import lv.tti.airdock.dto.RegistrationDto
import lv.tti.airdock.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController {

	@Autowired lateinit var userService: UserService
	@Autowired lateinit var sessionService: SessionService
	@Autowired lateinit var registrationService: RegistrationService

	@GetMapping("/workers")
	fun getWorkers() : List<UserDto> = userService.getWorkers().map { it.toUserDto() }

	@PostMapping("worker/register")
	fun register(@RequestBody @Valid userDto : RegistrationDto) : UserDto = registrationService.registerWorker(userDto).toUserDto()

	fun User.toUserDto() = UserDto(
			name = this.name
	)
}