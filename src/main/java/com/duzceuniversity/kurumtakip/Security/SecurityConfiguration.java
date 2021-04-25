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
                .antMatchers("/SifreUnuttum.html").permitAll()
                .antMatchers("/403.html").permitAll()
                .antMatchers("/FirmaBasvuru/**").permitAll()
                .antMatchers("/profile/**").hasAnyRole("YUK_USER", "YUK_ALT", "TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")

                .antMatchers("/arac/ekle").hasAnyRole("YUK_USER", "YUK_ALT", "TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/arac/kiralikarac").hasAnyRole("YUK_USER", "YUK_ALT", "TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/arac/topluEkleme").hasAnyRole("YUK_USER", "YUK_ALT", "TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/arac/araclar").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT", "TARIFELI_USER", "TARIFELI_BILDIRIM", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TERMINAL_BILDIRIM", "TARIFESIZ_USER", "TARIFESIZ_ALT", "TARIFESIZ_BILDIRIM", "ADMIN")

                .antMatchers("/personel/update/*").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT", "TARIFELI_USER", "TARIFELI_BILDIRIM", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TERMINAL_BILDIRIM", "TARIFESIZ_USER", "TARIFESIZ_ALT", "TARIFESIZ_BILDIRIM", "ADMIN")
                .antMatchers("/personel/ekle").hasAnyRole("YUK_USER", "YUK_ALT", "TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/personel/personeller").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT", "TARIFELI_USER", "TARIFELI_BILDIRIM", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TERMINAL_BILDIRIM", "TARIFESIZ_USER", "TARIFESIZ_ALT", "TARIFESIZ_BILDIRIM", "ADMIN")
                .antMatchers("/personel/topluEkle").hasAnyRole("YUK_USER", "YUK_ALT", "TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                //todo : ???????????
                .antMatchers("/CariFirma/**").hasAnyRole("YUK_USER", "YUK_MUHASEBE", "TARIFELI_USER","TARIFELI_MUHASEBE", "TERMINAL_USER", "TERMİNAL_MUHASEBE", "TARIFESIZ_USER", "TARIFESIZ_MUHASEBE", "ADMIN")
                .antMatchers("/kasaetikettablo/**").hasAnyRole("YUK_USER","YUK_MUHASEBE", "TARIFELI_USER","TARIFELI_MUHASEBE", "TERMINAL_USER", "TERMİNAL_MUHASEBE", "TARIFESIZ_USER", "TARIFESIZ_MUHASEBE", "ADMIN")
                .antMatchers("/gelirgidermuh/**").hasAnyRole("YUK_USER","YUK_MUHASEBE", "TARIFELI_USER","TARIFELI_MUHASEBE", "TERMINAL_USER", "TERMİNAL_MUHASEBE", "TARIFESIZ_USER", "TARIFESIZ_MUHASEBE", "ADMIN")
                .antMatchers("/muhasebe/**").hasAnyRole("YUK_USER","YUK_MUHASEBE", "TARIFELI_USER","TARIFELI_MUHASEBE", "TERMINAL_USER", "TERMİNAL_MUHASEBE", "TARIFESIZ_USER", "TARIFESIZ_MUHASEBE", "ADMIN")
                .antMatchers("/fatura/**").hasAnyRole("YUK_USER","YUK_MUHASEBE", "TARIFELI_USER","TARIFELI_MUHASEBE", "TERMINAL_USER", "TERMİNAL_MUHASEBE", "TARIFESIZ_USER", "TARIFESIZ_MUHASEBE", "ADMIN")
                .antMatchers("/irsaliye/**").hasAnyRole("YUK_USER","YUK_MUHASEBE", "TARIFELI_USER","TARIFELI_MUHASEBE", "TERMINAL_USER", "TERMİNAL_MUHASEBE", "TARIFESIZ_USER", "TARIFESIZ_MUHASEBE", "ADMIN")
                .antMatchers("/genel/**").hasAnyRole("YUK_USER","YUK_ALT","TARIFELI_USER", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/yonetim/**").hasAnyRole("ADMIN", "YONETIM", "YUK_USER","YUK_ALT","TARIFELI_USER", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT")
                .antMatchers("/kullanici/**").hasAnyRole("YUK_USER","YUK_ALT","TARIFELI_USER", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/kulekle/**").hasAnyRole("YUK_USER","TARIFELI_USER","TERMINAL_USER","TARIFESIZ_USER","ADMIN")
                .antMatchers("/uyelik/**").hasAnyRole("YUK_USER","YUK_ALT","TARIFELI_USER", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                //todo:'''''''
                .antMatchers("/takip/**").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT", "TARIFELI_USER", "TARIFELI_BILDIRIM", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_BILDIRIM", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_BILDIRIM", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/adr/**").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT", "ADMIN")

                .antMatchers("/iletisim/**").authenticated()
                .antMatchers("/anasayfa/**").authenticated()   //Anasayfaya authentica olmuş herkes erişebilir.
                .antMatchers("/oneri/**").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT", "TARIFELI_USER", "TARIFELI_BILDIRIM", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_BILDIRIM", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_BILDIRIM", "TARIFESIZ_ALT", "ADMIN")

                .antMatchers("/acente/**").hasAnyRole("YUK_USER", "YUK_ALT","TARIFELI_USER", "TARIFELI_ALT", "TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT", "ADMIN")
                .antMatchers("/teknikdestek/**").hasAnyRole("YUK_USER", "YUK_BILDIRIM", "YUK_ALT","TARIFELI_USER", "TARIFELI_ALT","TERMINAL_USER", "TERMINAL_ALT", "TARIFESIZ_USER", "TARIFESIZ_ALT","ADMIN")
                .antMatchers("/yuk/**").hasAnyRole("YUK_USER","YUK_ALT","YUK_BILDIRIM","ADMIN")
                .antMatchers("/danisman/bilgi").hasAnyRole("DANISMAN_USER,ADMIN")
                .antMatchers("/tarifesiz/**").hasAnyRole("TARIFESIZ_USER", "TARIFESIZ_ALT", "TARIFESIZ_BILDIRIM","ADMIN")
                .antMatchers("/terminal/**").hasAnyRole("TERMINAL_USER", "TERMINAL_BILDIRIM", "TERMINAL_ALT","ADMIN")
                .antMatchers("/tarifeli/**").hasAnyRole("TARIFELI_USER", "TARIFELI_ALT", "TARIFELI_BILDIRIM","ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN")
                .antMatchers("/error").authenticated()
                
                
               /* .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .antMatchers("/api/public/users").hasRole("ADMIN")*/
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/anasayfa/dash", true)
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
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
