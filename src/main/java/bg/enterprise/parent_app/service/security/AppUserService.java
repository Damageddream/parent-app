package bg.enterprise.parent_app.service.security;

import bg.enterprise.parent_app.model.AppUser;
import bg.enterprise.parent_app.model.dto.RegisterRequest;
import bg.enterprise.parent_app.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);
        log.info("Registered user: [id:{}]", user.getId());

        return jwtTokenService.generateToken(user.getUsername());
    }
}