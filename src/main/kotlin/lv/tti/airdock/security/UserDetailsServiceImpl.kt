package lv.tti.airdock.security

import lv.tti.airdock.core.database.CredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired lateinit var credentialsRepository: CredentialsRepository

    override fun loadUserByUsername(username: String): UserDetails {

        val credentialsOpt = credentialsRepository.findByLogin(username.toUpperCase())

        if (credentialsOpt.isPresent) {

            val credentials = credentialsOpt.get()
            val login = credentials.login
            val password = credentials.password

            return AppUser(login, password, credentials, emptyList())
        }
        throw UsernameNotFoundException(username)
    }
}