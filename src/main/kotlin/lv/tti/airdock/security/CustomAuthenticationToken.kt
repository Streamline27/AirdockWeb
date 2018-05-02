package lv.tti.airdock.security

import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import org.springframework.data.authentication.UserCredentials
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CustomAuthenticationToken(
        login: String,
        password: String,
        authorities: Collection<GrantedAuthority>,
        val credentials: Credentials) : UsernamePasswordAuthenticationToken(login, password, authorities)