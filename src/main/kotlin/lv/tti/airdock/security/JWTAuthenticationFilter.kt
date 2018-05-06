package lv.tti.airdock.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.rest.LoginDto
import org.springframework.data.authentication.UserCredentials
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(var authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter()
{

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {

        val credentials = ObjectMapper().readValue(request.inputStream, LoginDto::class.java)

        return authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.login,
                        credentials.password,
                        emptyList()
                )
        )
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain?,
                                          authResult: Authentication) {

        val token = Jwts.builder()
                .setSubject((authResult.principal as UserDetails).username)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact()

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+token)
    }
}