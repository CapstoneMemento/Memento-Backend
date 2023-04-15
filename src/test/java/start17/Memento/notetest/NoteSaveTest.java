package start17.Memento.notetest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import start17.Memento.model.dto.note.NoteSaveRequestDto;
import start17.Memento.domain.note.Note;
import start17.Memento.domain.note.NoteRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteSaveTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NoteRepository noteRepository;

    @After
    public void tearDown() throws Exception {
        noteRepository.deleteAll();
    }

    @Test
    public void NotePost() throws Exception {
        //given
        int categories_id = 1;
        String content = "content";
        String title = "title";
        String type = "type";
        NoteSaveRequestDto requestDto = NoteSaveRequestDto.builder()
                .categories_id(categories_id)
                .content(content)
                .title(title)
                .type(type)
                .build();
        String url = "http://localhost:" + port + "/note/add";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Note> all = noteRepository.findAll();
        assertThat(all.get(0).getCategories_id()).isEqualTo(categories_id);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getType()).isEqualTo(type);


    }
}
