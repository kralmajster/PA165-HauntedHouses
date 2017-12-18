package fi.muni.pa165.hauntedhouses.config;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fi.muni.pa165.hauntedhouses.enums.Role;
import fi.muni.pa165.hauntedhouses.security.CustomAuthenticationProvider;
import fi.muni.pa165.hauntedhouses.security.RestAuthenticationEntryPoint;

/**
 * @author Mario Majernik, 422165
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {CustomAuthenticationProvider.class})
@Import(RestConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Inject
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()

                //.antMatchers(HttpMethod.GET, "/rest/**").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/house").hasAnyRole(Role.ADMIN.name(), Role.OWNER.name(), Role.RESIDENT.name())
                .antMatchers(HttpMethod.PUT, "/rest/house").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/rest/house").hasAnyRole(Role.ADMIN.name(), Role.OWNER.name())
                .antMatchers(HttpMethod.DELETE, "/rest/house").hasRole(Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/rest/house/newhouse").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/rest/house/newhouse").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/rest/house/newhouse").hasRole(Role.ADMIN.name())
                
                .antMatchers(HttpMethod.GET, "/rest/ghost").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/rest/ghost").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/rest/ghost").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/rest/ghost").hasRole(Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/rest/ability").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/rest/ability").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/rest/ability").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/rest/ability").hasRole(Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/rest/person").hasAnyRole(Role.ADMIN.name(), Role.OWNER.name(), Role.RESIDENT.name())

                .antMatchers("/js/**").permitAll()
                .antMatchers("/partials/**").permitAll()
                .antMatchers("/index.html").permitAll()
                
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login.html").permitAll().and()
                .logout().logoutUrl("/logout.html").logoutSuccessUrl("/index.html?logout").permitAll().and().csrf().disable();
    }

}
