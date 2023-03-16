package start17.Memento.model.dto.note;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoteUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public NoteUpdateRequestDto(String title, String content){
        this.title= title;
        this.content = content;
    }
}
