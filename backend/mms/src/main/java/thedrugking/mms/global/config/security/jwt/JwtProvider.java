package thedrugking.mms.global.config.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thedrugking.mms.domain.user.dao.UserRepository;
import thedrugking.mms.domain.user.domain.User;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

	private static final int ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
	private final JwtProperty jwtProperty;
	private final UserRepository userRepository;
	private Key key;
	private JwtParser jwtParser;

	@PostConstruct
	private void init() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtProperty.getSecretKey());
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
	}

	public String createToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setHeader(createHeader())
			.setClaims(Map.of("username", username))
			.setExpiration(createExpiredDate())
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			jwtParser.parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	public Authentication getAuthentication(String accessToken) {
		Claims claims = jwtParser.parseClaimsJws(accessToken).getBody();
		String username = (String)claims.get("username");

		User user = getUser(username);
		JwtUserDetails jwtUserDetails = new JwtUserDetails(user);

		return new UsernamePasswordAuthenticationToken(
			jwtUserDetails,
			user.getPassword(),
			jwtUserDetails.getAuthorities());
	}

	private User getUser(String loginId) {
		if (loginId == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}

		return userRepository.findByLoginId(loginId)
			.orElseThrow(() -> new IllegalArgumentException("없는 유저"));
	}

	private Map<String, Object> createHeader() {
		return Map.of(
			"typ", "JWT",
			"alg", "HS256"
		);
	}

	private Date createExpiredDate() {
		Date ext = new Date();
		ext.setTime(ext.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
		return ext;
	}
}

