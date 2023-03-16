package start17.Memento.model.dto.note;

import lombok.Getter;
import start17.Memento.domain.note.Note;

@Getter
public class NoteResponseDto {
    private Long id;
    private String title;
    private String content;
    private String type;
    private int categoies_id;

    public NoteResponseDto(Note entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.type = entity.getType();
        this.categoies_id = entity.getCategories_id();
    }
}
