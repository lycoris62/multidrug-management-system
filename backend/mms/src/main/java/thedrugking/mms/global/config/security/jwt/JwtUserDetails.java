package thedrugking.mms.global.config.security.jwt;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;
import thedrugking.mms.domain.user.domain.Patient;
import thedrugking.mms.domain.user.domain.User;
import thedrugking.mms.domain.user.dto.UserType;

@RequiredArgsConstructor
public class JwtUserDetails implements UserDetails {

	private final User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		String userType = user instanceof Patient ? UserType.PATIENT.name() : UserType.MEDICAL_PERSON.name();
		return List.of(new SimpleGrantedAuthority(userType));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
