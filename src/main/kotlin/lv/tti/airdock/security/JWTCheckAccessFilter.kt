package lv.tti.airdock.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import lv.tti.airdock.security.utilities.HEADER_STRING
import lv.tti.airdock.security.utilities.SECRET
import lv.tti.airdock.security.utilities.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Authorization
 */
class JWTCheckAccessFilter(authManager : AuthenticationManager) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {

        val header = request.getHeader(HEADER_STRING)

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().authentication = getAuthentication(request)
        chain.doFilter(request, response)
    }

    private fun getAuthentication(req : HttpServletRequest) : UsernamePasswordAuthenticationToken?{
        try {
            val token = req.getHeader(HEADER_STRING)
            if (token != null) {

                val username = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .body
                        .subject

                if (username != null) {
                    /** TODO: https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
                     *
                     *  Replace this with Custom token
                     */
                    return UsernamePasswordAuthenticationToken(username, null, mutableListOf())
                }
                return null
            }
        }
        catch (e : ExpiredJwtException) {
            return null
        }
        return null
    }


}