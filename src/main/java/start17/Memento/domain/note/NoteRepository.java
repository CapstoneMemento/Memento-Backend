package start17.Memento.domain.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCategoryID(@Param("categories_id") int categories_id, @Param("userid") String userid);
    List<Note> findByUserID(@Param("userid") String userid);
}
