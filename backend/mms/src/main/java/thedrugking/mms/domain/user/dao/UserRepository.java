package thedrugking.mms.domain.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import thedrugking.mms.domain.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLoginId(String loginId);
}
