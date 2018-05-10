package lv.tti.airdock.config

import lv.tti.airdock.security.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder

import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * https://gist.github.com/zmts/802dc9c3510d79fd40f9dc38a12bccfc
 */
@Configuration
@EnableWebSecurity
@Profile(value = ["dev"])
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired lateinit var userDetailsProvidingService: UserDetailsProvidingService

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            .and()
                .addFilter(JWTCheckPasswordFilter(authenticationManager()))
                .addFilter(JWTCheckAccessFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsProvidingService).passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

    /**
     * Enable requests from all domains. Will be useful when deploying
     */
    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }

}