package bg.enterprise.parent_app.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public String login(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            return jwtTokenService.generateToken(username);
        } else {
            return null;
        }
    }
}