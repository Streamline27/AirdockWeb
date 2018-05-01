package lv.tti.airdock.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@Profile(value = ["demo"])
class DemoSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().permitAll()
    }
}