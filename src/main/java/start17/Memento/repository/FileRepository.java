package start17.Memento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start17.Memento.domain.s3.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
