package lv.tti.airdock.security

import lv.tti.airdock.core.database.CredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired lateinit var credentialsRepository: CredentialsRepository

    override fun loadUserByUsername(username: String): UserDetails {

        val credentials = credentialsRepository.findByLogin(username.toUpperCase())
        if (credentials.isPresent) {
            return User(credentials.get().login, credentials.get().password, emptyList())
        }
        throw UsernameNotFoundException(username)
    }
}