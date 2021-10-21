package com.smalltalk.wstest.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {
    @Autowired lateinit var userService: UserDetailsService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/css/**","/js/**","/img/**","/lib/**",)
    }

    override fun configure(http: HttpSecurity?) {
        http {
            httpBasic {}
            authorizeRequests {
                authorize("/admin/**", hasAuthority("ADMIN"))
                authorize("/user/**", hasAuthority("USER"))
                authorize("/**", permitAll)
            }
            formLogin {
                loginPage = "/login"
                defaultSuccessUrl("/dashboard", true)
                permitAll()
            }
            logout {
                logoutRequestMatcher = AntPathRequestMatcher("/logout")
                logoutSuccessUrl = "/login"
                invalidateHttpSession = true
            }
            exceptionHandling {
                accessDeniedPage = "/denied"
            }
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userService)?.passwordEncoder(passwordEncoder())
    }
}
