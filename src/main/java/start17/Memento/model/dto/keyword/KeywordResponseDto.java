package start17.Memento.model.dto.keyword;

import lombok.Getter;
import start17.Memento.domain.keyword.Keyword;
import start17.Memento.domain.note.Note;

@Getter
public class KeywordResponseDto {
    private Long id;
    private Long noteid;
    int first;
    int last;

    public KeywordResponseDto(Keyword entity){
        this.id = entity.getId();
        this.noteid = entity.getNoteid();
        this.first = entity.getFirst();
        this.last = entity.getLast();
    }
}
