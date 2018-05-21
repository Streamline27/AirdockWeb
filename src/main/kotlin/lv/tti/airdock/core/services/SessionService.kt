package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.UserRepository
import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.security.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class SessionService {

    @Autowired lateinit var userDao : UserRepository

    fun getActiveUser() : Optional<User>  {

        val details = SecurityContextHolder.getContext().authentication.details
        if (details is HashMap<*, *>) {
            val userId = details.get("userId") as Int
            return userDao.findById(userId.toLong())
        }
        else return Optional.empty()
    }

    fun getActiveUserCredentials() : Optional<Credentials> {

        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is UserDetailsImpl) return Optional.of(principal.credentials)
        else return Optional.empty()
    }

}