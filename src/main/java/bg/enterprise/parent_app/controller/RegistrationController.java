package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.RegisterRequest;
import bg.enterprise.parent_app.service.security.AppUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        log.info("Register request: {}", request);
        String token = appUserService.registerUser(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Data
    @AllArgsConstructor
    public static class JwtResponse {
        private String token;
    }
}