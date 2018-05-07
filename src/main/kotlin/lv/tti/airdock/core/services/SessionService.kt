package lv.tti.airdock.core.services

import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.security.AppUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SessionService {

    fun getActiveUser() : Optional<User>  {

        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is AppUser) return Optional.of(principal.credentials.user)
        else return Optional.empty()
    }

    fun getActiveUserCredentials() : Optional<Credentials> {

        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is AppUser) return Optional.of(principal.credentials)
        else return Optional.empty()
    }

}