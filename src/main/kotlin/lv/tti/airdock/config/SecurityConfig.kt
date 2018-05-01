package lv.tti.airdock.config

import lv.tti.airdock.security.RestAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

@Configuration
@EnableWebSecurity
@Profile(value = ["dev"])
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired lateinit var restAuthenticationEntryPoint : RestAuthenticationEntryPoint

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            .and()
                .httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
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