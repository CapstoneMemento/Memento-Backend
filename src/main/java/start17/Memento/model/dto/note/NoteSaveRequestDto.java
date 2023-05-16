package start17.Memento.model.dto.note;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import start17.Memento.domain.note.Note;

@Getter
@NoArgsConstructor
public class NoteSaveRequestDto {
    private int categories_id;
    private String content;
    private String title;
    private String type;
    private String userid;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Builder
    public NoteSaveRequestDto(int categories_id, String content, String title, String type,String userid){
        this.categories_id = categories_id;
        this.content=content;
        this.title = title;
        this.type = type;
        this.userid = userid;
    }

    public Note toEntity() {
        return Note.builder()
                .categories_id(categories_id)
                .content(content)
                .title(title)
                .type(type)
                .userid(userid)
                .build();
    }
}
