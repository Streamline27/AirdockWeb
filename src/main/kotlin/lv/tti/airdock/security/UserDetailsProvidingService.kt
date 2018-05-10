package lv.tti.airdock.security

import lv.tti.airdock.core.database.CredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * Fetches user credentials that should be compared to those that are passed by url
 */
@Component
class UserDetailsProvidingService : UserDetailsService {

    @Autowired lateinit var credentialsRepository: CredentialsRepository

    override fun loadUserByUsername(username: String): UserDetails {

        val credentialsOpt = credentialsRepository.findByLogin(username.toUpperCase())

        if (credentialsOpt.isPresent) {

            val credentials = credentialsOpt.get()
            val login = credentials.login
            val password = credentials.password

            return UserDetailsImpl(login, password, credentials, emptyList())
        }
        throw UsernameNotFoundException(username)
    }
}