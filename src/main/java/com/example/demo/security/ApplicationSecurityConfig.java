package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/managemet/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST,"/managemet/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT,"/managemet/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
                .antMatchers(HttpMethod.GET,"/managemet/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
       UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
               .roles(ApplicationUserRole.STUDENT.name()) //ROLE_STUDENT
                .build();

       UserDetails lindauser = User.builder()
               .username("linda")
               .password(passwordEncoder.encode("password123"))
               .roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMIN
               .build();

       UserDetails tomuser = User.builder()
               .username("tom")
               .password(passwordEncoder.encode("password321"))
               .roles(ApplicationUserRole.ADMINTRAINEE.name()) //ROLE_ADMINTRAINEE
               .build();


        return new InMemoryUserDetailsManager(
                annaSmithUser, lindauser,tomuser
        );
    }
}
