package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.TaskRepository
import lv.tti.airdock.core.database.UserRepository
import lv.tti.airdock.core.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

	@Autowired
	lateinit var repository: UserRepository

	fun getWorkers() = repository.findUserByRole(User.Role.WORKER)

	fun getById(id: Long) = repository.getOne(id)
}