package start17.Memento.notetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import start17.Memento.domain.note.Note;
import start17.Memento.domain.note.NoteRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteRepositoryTest {
    @Autowired
    NoteRepository noteRepository;
/*
    @After
    public void cleanup(){
        noteRepository.deleteAll();
    }
*/
    @Test
    public void get(){
        //given
        String title = "테스트 제목";
        String content = "테스트 본문";
        String type = "키워드";
        int categories_id = 1;
        noteRepository.save(Note.builder()
                .categories_id(categories_id)
                .content(content)
                .title(title)
                .type(type)
                .build());
        //when
        List<Note> noteList = noteRepository.findAll();

        //then
        Note note = noteList.get(0);
        assertThat(note.getTitle()).isEqualTo(title);
        assertThat(note.getContent()).isEqualTo(content);

    }
}
