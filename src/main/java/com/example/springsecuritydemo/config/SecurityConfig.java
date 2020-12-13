package com.example.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccessDeniedHandler demoAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private LogoutSuccessHandler testLogoutSuccessHandler;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//            .exceptionHandling(exceptionHandle -> exceptionHandle
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())ini
//            )
            .authorizeRequests(auth -> auth
                .antMatchers("/test/**","/invalid", "/expired").permitAll()
                .anyRequest().authenticated()
            ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(testLogoutSuccessHandler)
                .permitAll()
            )
            .addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAt(loginAuthenticationNo2Filter(), UsernamePasswordAuthenticationFilter.class);
//            .addFilterAfter(expiredSessionFilter(), SessionManagementFilter.class);
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/invalid").maximumSessions(1).expiredUrl("/expired")
//                .and()
//                .formLogin().permitAll()
//                .defaultSuccessUrl("/test")
//                .loginProcessingUrl("/performlogin")
//                 .successHandler(successHandler)
//                .and().exceptionHandling().accessDeniedHandler(demoAccessDeniedHandler)

    }


    @Bean
    LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setFilterProcessesUrl("/performlogin");
        return filter;
    }

    @Bean
    LoginAuthenticationFilter loginAuthenticationNo2Filter() throws Exception {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setFilterProcessesUrl("/performloginNo2");
        return filter;
    }

    private Filter expiredSessionFilter() {
        SessionManagementFilter smf = new SessionManagementFilter(new HttpSessionSecurityContextRepository());
        smf.setInvalidSessionStrategy((request, response) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "SESSION_TIME_OUT"));

        return smf;
    }

}
