package com.leverx.kostusev.dealerstat.config;

import com.leverx.kostusev.dealerstat.service.DealerStatUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

import static com.leverx.kostusev.dealerstat.entity.Role.ADMIN;
import static com.leverx.kostusev.dealerstat.entity.Role.TRADER;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTHORIZED_USERS = {ADMIN.getName(), TRADER.getName()};
    private static final String[] ADMIN_PAGES = {"/admin/**"};
    private static final String[] AUTHORIZED_USER_PAGES = {"/articles/**", "/games/**"};
    private static final String[] COOKIES = {"JSESSIONID"};
    private static final String[] IGNORED_URLS = {"/articles/{id}/comments"};

    private final DealerStatUserDetailsService detailsService;
    private final PasswordEncoder passwordEncoder;
    private final EncodingConfig encodingConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingConfig.getEncodingFilter(), CsrfFilter.class);
//        @formatter:off
                http
                    .csrf().disable()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.DELETE, ADMIN_PAGES)
                            .hasAuthority(ADMIN.getName())
                        .antMatchers(HttpMethod.POST, AUTHORIZED_USER_PAGES)
                            .hasAnyAuthority(AUTHORIZED_USERS)
                        .antMatchers(HttpMethod.PUT, AUTHORIZED_USER_PAGES)
                            .hasAnyAuthority(AUTHORIZED_USERS)
                        .antMatchers(HttpMethod.DELETE, AUTHORIZED_USER_PAGES)
                            .hasAnyAuthority(AUTHORIZED_USERS)
                        .anyRequest()
                            .permitAll()
                    .and()
                        .formLogin()
                    .and()
                        .logout()
                        .invalidateHttpSession(true)
                        .deleteCookies(COOKIES)
                    .and()
                        .httpBasic();
        // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(IGNORED_URLS);
    }
}
