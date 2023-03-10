package start17.Memento.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import start17.Memento.repository.Note;

@Getter
@NoArgsConstructor
public class NoteSaveRequestDto {
    private int categories_id;
    private String content;
    private String title;
    private String type;

    @Builder
    public NoteSaveRequestDto(int categories_id, String content, String title, String type){
        this.categories_id = categories_id;
        this.content=content;
        this.title = title;
        this.type = type;
    }

    public Note toEntity() {
        return Note.builder()
                .categories_id(categories_id)
                .content(content)
                .title(title)
                .type(type)
                .build();
    }
}
