package com.example.myFirstOwnSpringBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //基于内存的用户存储
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
//                withUser("不想加班").password(new BCryptPasswordEncoder().encode("123")).authorities("ROLE_USER").and().
//                withUser("我想快乐").password(new BCryptPasswordEncoder().encode( "456")).authorities("ROLE_USER");

        //基于JDBC的用户存储
//        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
//                "select username, password, enabled from Users where username = ?").authoritiesByUsernameQuery(
//                "select username, authority from UserAuthorities where username = ?"
//        ).passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        //以LDAP作为后端的用户存储
//        LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldap = auth.ldapAuthentication();
//        ldap.userSearchBase("ou=people").userSearchFilter("(uid={0})").groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}").passwordCompare().passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode");
//        ldap.contextSource().root("dc=tacocloud, dc=com").ldif("classpath:users.ldif");

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll").and().formLogin().loginPage("/login")
                //.loginProcessingUrl("/authenticate").usernameParameter("user").passwordParameter("pwd")
                .defaultSuccessUrl("/design", true)
                .and().logout().logoutSuccessUrl("/login");
    }

}
