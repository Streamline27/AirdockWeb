package lv.tti.airdock.core.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
final class Services {
	@Autowired lateinit var userService			: UserService
	@Autowired lateinit var workOrderService	: WorkOrderService
	@Autowired lateinit var requestService		: RequestService
	@Autowired lateinit var taskService			: TaskService
	@Autowired lateinit var registrationService	: RegistrationService

	init {
		ServiceKeeper.services = this
	}

}

object ServiceKeeper {
	lateinit var services: Services

	val userService: UserService
		get() = services.userService
	val workOrderService: WorkOrderService
		get() = services.workOrderService
	val requestService: RequestService
		get() = services.requestService
	val taskService: TaskService
		get() = services.taskService
	val registrationService: RegistrationService
		get() = services.registrationService
}