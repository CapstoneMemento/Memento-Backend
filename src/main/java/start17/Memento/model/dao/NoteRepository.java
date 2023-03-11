package start17.Memento.model.dao;

import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import start17.Memento.domain.note.Note;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface NoteRepository {

    @Insert("insert into note(title, content values (#{title}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Note note);

    @Select("select * from note where id=#{id}")
    public Note findById (Long id);

    @Select("select * from note")
    public List<Note> findAll();

    @Update("update note set title=#{title}, content=#{content} where id=#{id}")
    public void updateNote (Long noteId, String title, String content);

    @Update("update note set category=#{category} where id=#{id}")
    public void updateCategory(Long noteId, int category_id);

    @Delete("delete * from note where id=#{id}")
    public void clearNote();

    @Delete("delete * from note")
    public void clearStore();

}
