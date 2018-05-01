package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface UserRepository : JpaRepository<User, Long> {

	fun findUserByRole(role: User.Role): List<User>
}