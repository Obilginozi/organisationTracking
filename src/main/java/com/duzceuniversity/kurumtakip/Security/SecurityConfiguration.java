package com.duzceuniversity.kurumtakip.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String[] resources = new String[]{
                "/", "/home","/pictureCheckCode","/include/**",
                "/css/**","/icons/**","/images/**","/js/**","/layer/**,/resources/**"
        };
        http	
        		.csrf().disable()
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN")
                .antMatchers("/error").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/giris").permitAll()
                .defaultSuccessUrl("/anasayfa/dash", true)
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/giris")
               /* .and()
                .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe").userDetailsService(userPrincipalDetailsService)
                */
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}