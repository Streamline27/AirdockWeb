package lv.tti.airdock.security

import lv.tti.airdock.core.domain.Credentials
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class UserDetailsImpl(
        username: String,
        password : String,
        val credentials : Credentials,
        authorities: Collection<GrantedAuthority>
) : User(username, password, authorities)