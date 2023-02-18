package start17.Memento.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import start17.Memento.model.dto.books;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<books> getAllDataList();
}
