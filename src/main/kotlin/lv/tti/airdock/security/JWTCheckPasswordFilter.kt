package lv.tti.airdock.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lv.tti.airdock.rest.dto.LoginDto
import lv.tti.airdock.security.utilities.EXPIRATION_TIME
import lv.tti.airdock.security.utilities.HEADER_STRING
import lv.tti.airdock.security.utilities.SECRET
import lv.tti.airdock.security.utilities.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Authentication
 */
class JWTCheckPasswordFilter(var authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter()
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

        val credentials = (authResult.principal as lv.tti.airdock.security.UserDetailsImpl).credentials

        val token = Jwts.builder()
                .setSubject((authResult.principal as UserDetails).username)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .claim("username", credentials.user.name)
                .claim("role", credentials.user.role)
                .compact()

        response.addHeader(HEADER_STRING, TOKEN_PREFIX +token)
        with(response.writer) {
            write("{}")
            flush()
            close()
        }
    }
}