package thedrugking.mms.global.config.security.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final String BEARER_PREFIX = "Bearer ";
	private final String HEADER_AUTHORIZATION = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		String rawAccessToken = request.getHeader(HEADER_AUTHORIZATION);
		log.info("raw token = {}", rawAccessToken);

		if (isValid(rawAccessToken)) {
			String accessToken = rawAccessToken.substring(7);
			log.info("access token = {}", accessToken);

			Authentication authentication = jwtProvider.getAuthentication(accessToken);
			log.info("authen = {}", authentication.getName());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private boolean isValid(String rawAccessToken) {

		if (!StringUtils.hasText(rawAccessToken)) {
			return false;
		}

		if (!rawAccessToken.startsWith(BEARER_PREFIX)) {
			return false;
		}

		String accessToken = rawAccessToken.substring(7);

		if (!StringUtils.hasText(accessToken)) {
			return false;
		}

		return jwtProvider.validateToken(accessToken);
	}
}
