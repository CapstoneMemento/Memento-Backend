package start17.Memento.domain.keyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByNoteid(@Param("noteid") Long noteid);
    @Transactional
    @Modifying
    @Query("delete from Keyword k where k.noteid = :noteid")
    void deleteByNoteid(@Param("noteid") Long noteid);

}
