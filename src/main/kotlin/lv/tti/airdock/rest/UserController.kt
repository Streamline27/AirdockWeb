package lv.tti.airdock.rest

import lv.tti.airdock.core.services.SessionService
import lv.tti.airdock.core.services.UserService
import lv.tti.airdock.core.utilities.UserException
import lv.tti.airdock.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {

	@Autowired lateinit var userService: UserService
	@Autowired lateinit var sessionService: SessionService

	@GetMapping("/workers")
	fun getWorkers() = userService.getWorkers()

	@PostMapping("/login")
	fun login() : ResponseEntity<UserDto> {

		val user = sessionService.getActiveUser()

		if (user.isPresent) {
			return ResponseEntity(UserDto(user.get().name), HttpStatus.OK)
		}
		return ResponseEntity(UserDto(), HttpStatus.BAD_REQUEST)
	}
}