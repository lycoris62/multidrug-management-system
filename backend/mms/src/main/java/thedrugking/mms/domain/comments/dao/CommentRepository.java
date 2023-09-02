package thedrugking.mms.domain.comments.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import thedrugking.mms.domain.comments.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
