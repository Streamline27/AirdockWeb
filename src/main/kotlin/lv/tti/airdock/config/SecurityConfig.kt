package lv.tti.airdock.config

import lv.tti.airdock.security.RestAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

//    @Autowired lateinit var restAuthenticationEntryPoint : RestAuthenticationEntryPoint

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()

                // Allow
//                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html")
//                .permitAll()
                .anyRequest().permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
    }

//    @Bean
//    override fun userDetailsService(): UserDetailsService {
//        val user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build()
//
//        return InMemoryUserDetailsManager(user)
//    }


}