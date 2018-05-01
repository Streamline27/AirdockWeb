package lv.tti.airdock.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component( "restAuthenticationEntryPoint" )
class RestAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {

    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
    ) {
        response.addHeader("WWW-Authenticate", "Basic realm=\"${ realmName }\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }

    override fun afterPropertiesSet() {
        realmName = "AirdockWeb"
        super.afterPropertiesSet()
    }
}