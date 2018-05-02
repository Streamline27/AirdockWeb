package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.CredentialsRepository
import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.security.CustomAuthenticationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SessionService {

    @Autowired
    lateinit var credentialsRepository : CredentialsRepository

    fun getCredentialsFor(login : String, password : String) : Optional<Credentials> {

        val credentials = credentialsRepository.findByLogin(login)

        if (credentials.isPresent && credentials.get().password == password) {
            return credentials
        }
        return Optional.empty()
    }

    fun getActiveUserCredentials() : Optional<Credentials> = Optional.ofNullable(getAuthentication()?.credentials)
    fun getActiveUser() : Optional<User> = Optional.ofNullable(getAuthentication()?.credentials?.user)

    private fun getAuthentication() : CustomAuthenticationToken? = SecurityContextHolder.getContext().authentication as? CustomAuthenticationToken
}