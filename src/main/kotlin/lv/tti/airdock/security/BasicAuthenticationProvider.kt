package lv.tti.airdock.security

import lv.tti.airdock.core.database.CredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class BasicAuthenticationProvider : AuthenticationProvider{

    @Autowired lateinit var credentialsRepository: CredentialsRepository

    override fun authenticate(authentication: Authentication): Authentication? {

        val suppliedLogin = authentication.name.toUpperCase()
        val suppliedPassword = authentication.credentials.toString()

        val userCredentials = credentialsRepository.findByLogin(suppliedLogin)

        if (userCredentials.isPresent && userCredentials.get().password == suppliedPassword) {
                return UsernamePasswordAuthenticationToken(suppliedLogin, suppliedPassword, emptyList())
        }
        return null
    }

    override fun supports(authentication: Class<*>) = authentication == UsernamePasswordAuthenticationToken::class.java
}