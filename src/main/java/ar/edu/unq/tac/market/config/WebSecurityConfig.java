package ar.edu.unq.tac.market.config;

/**
 * Created by frepond on 7/2/14.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.unq.tac.market.validators.EventValidator;


@EnableWebSecurity
@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(tacPasswordsEncoder());
       
    }
    
    @Value("${rest.base_path}")
    private String base_path;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//this should be handled from AngularJS
        http.csrf().disable();
        
        // Seguridad http 	
        http.authorizeRequests()
            .antMatchers("/**/sessionUser/**").hasRole("ADMIN")
            .antMatchers("/**/sessionManager/**").hasAnyRole("ADMIN")
            .antMatchers("/**/hal-browser/**").anonymous()
           
            .antMatchers("/**/event/**").anonymous()
            .antMatchers("/**/api/v1/event/**").anonymous()
            
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    
    @Bean
    public PasswordEncoder tacPasswordsEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    EventValidator eventValidator() {
        return new EventValidator();
    }
    
}