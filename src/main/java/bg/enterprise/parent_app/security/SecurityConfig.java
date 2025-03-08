package bg.enterprise.parent_app.security;

import bg.enterprise.parent_app.service.security.CustomUserDetailsService;
import bg.enterprise.parent_app.service.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final JwtTokenService jwtTokenService; // Injected by Spring
	//	private final AppUserRepository appUserRepository;
	//
	//	public SecurityConfig(AppUserRepository appUserRepository) {
	//		this.appUserRepository = appUserRepository;
	//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Using customUserDetailsService instead of the in-memory version
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenService, customUserDetailsService);
		//		JwtOAuth2AuthenticationSuccessHandler successHandler =
		//			new JwtOAuth2AuthenticationSuccessHandler(jwtTokenService, customUserDetailsService, appUserRepository);

		http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/register"/*, "/oauth2/**"*/).permitAll()
						.anyRequest().authenticated())
				//		.oauth2Login(oauth2 -> oauth2.successHandler(successHandler))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}