package thedrugking.mms.global.config.security;

import java.util.Collections;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thedrugking.mms.global.config.security.jwt.JwtFilter;
import thedrugking.mms.global.config.security.jwt.JwtProvider;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtProvider jwtProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			.requestMatchers(PathRequest.toH2Console())
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable);
		http.httpBasic(AbstractHttpConfigurer::disable);
		http.cors(getCorsConfiguration());
		http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests(authz -> authz.requestMatchers("/api/signup", "/api/login").permitAll());
		http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated());

		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	private Customizer<CorsConfigurer<HttpSecurity>> getCorsConfiguration() {
		return cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(Collections.singletonList("*"));
			config.setAllowedMethods(Collections.singletonList("*"));
			config.setAllowedHeaders(Collections.singletonList("*"));
			return config;
		});
	}

	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter(jwtProvider);
	}
}
