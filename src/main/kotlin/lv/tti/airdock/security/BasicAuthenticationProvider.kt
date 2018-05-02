package lv.tti.airdock.security

import lv.tti.airdock.core.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class BasicAuthenticationProvider : AuthenticationProvider{

    @Autowired lateinit var sessionService: SessionService

    override fun authenticate(authentication: Authentication): Authentication? {

        val suppliedLogin = authentication.name.toUpperCase()
        val suppliedPassword = authentication.credentials.toString()

        val userCredentials = sessionService.getCredentialsFor(suppliedLogin, suppliedPassword)

        if (userCredentials.isPresent) {
                return CustomAuthenticationToken(
                        login = suppliedLogin,
                        password = suppliedPassword,
                        authorities = emptyList(),
                        credentials = userCredentials.get())
        }
        return null
    }

    override fun supports(authentication: Class<*>) = authentication == UsernamePasswordAuthenticationToken::class.java
}