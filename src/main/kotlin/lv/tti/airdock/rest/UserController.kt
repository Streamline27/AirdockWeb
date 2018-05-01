package lv.tti.airdock.rest

import lv.tti.airdock.core.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {

	@Autowired
	lateinit var userService: UserService

	@GetMapping("/workers")
	fun getWorkers() = userService.getWorkers()
}