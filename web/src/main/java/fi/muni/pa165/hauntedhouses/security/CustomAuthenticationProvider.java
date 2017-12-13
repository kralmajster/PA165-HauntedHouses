package fi.muni.pa165.hauntedhouses.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.facade.PersonFacade;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private PersonFacade personFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PersonDTO user = (PersonDTO) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        if (personFacade.authenticate(user, password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));

            return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
        } else {
            throw new BadCredentialsException("Invalid password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
