package nl.avansc1.facturatie.config;

import nl.avansc1.facturatie.repository.UserDAO;
import nl.avansc1.facturatie.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by Robin on 27/10/2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new CustomUserDetailsService(userDAO)).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/vendor/**").permitAll()
//                .anyRequest().permitAll();
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .usernameParameter("email").passwordParameter("password")
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout")
                .logoutUrl("/logout").and()
            .csrf().disable();
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder(12);
    }
}