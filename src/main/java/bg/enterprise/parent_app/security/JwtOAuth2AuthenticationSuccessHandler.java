//package bg.enterprise.parent_app.security;
//
//import bg.enterprise.parent_app.model.entity.AppUser;
//import bg.enterprise.parent_app.repository.AppUserRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//import java.util.Optional;
//
//public class JwtOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    private final JwtTokenService jwtTokenService;
//    private final UserDetailsService userDetailsService;
//    private final AppUserRepository appUserRepository;
//
//    public JwtOAuth2AuthenticationSuccessHandler(JwtTokenService jwtTokenService,
//                                                 UserDetailsService userDetailsService,
//                                                 AppUserRepository appUserRepository) {
//        this.jwtTokenService = jwtTokenService;
//        this.userDetailsService = userDetailsService;
//        this.appUserRepository = appUserRepository;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        // Use OAuth2User instead of DefaultOAuth2User.
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        String email = (String) oAuth2User.getAttributes().get("email");
//
//        // Check if the user exists; if not, persist a new user.
//        Optional<AppUser> existingUser = appUserRepository.findByEmail(email);
//        if (existingUser.isEmpty()) {
//            AppUser newUser = new AppUser();
//            newUser.setUsername(email); // or extract another attribute if available
//            newUser.setEmail(email);
//            appUserRepository.save(newUser);
//        }
//
//        // Generate a JWT token and return it in the response header.
//        String token = jwtTokenService.generateToken(email);
//        response.addHeader("Authorization", "Bearer " + token);
//    }
//}