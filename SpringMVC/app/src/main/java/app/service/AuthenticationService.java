package app.service;

import app.model.CustomUserBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Slf4j(topic = "AuthenticationServiceLog")
public class AuthenticationService implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // TODO: Authenticate

        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        // Although in the xml configuration file, the roles don't have prefix,
        // spring-security will add "ROLE_" to them as the prefix by default.
        // So we need to add "ROLE_" to user role here so that they can match.
        // For reference, please check hasAnyRole(String... roles) in spring-security
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new CustomUserBean(name), password, grantedAuths);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

