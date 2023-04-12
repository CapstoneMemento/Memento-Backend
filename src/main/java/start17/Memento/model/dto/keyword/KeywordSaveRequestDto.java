package start17.Memento.model.dto.keyword;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import start17.Memento.domain.keyword.Keyword;
import start17.Memento.domain.note.Note;

@Getter
@NoArgsConstructor
public class KeywordSaveRequestDto {
    private Long noteid;
    int first; // 시작인덱스
    int last; //종결 인덱스

    @Builder
    public KeywordSaveRequestDto(int first, int last, Long noteid){
        this.first = first;
        this.last = last;
        this.noteid = noteid;
    }

    public Keyword toEntity() {
        return Keyword.builder()
                .first(first)
                .last(last)
                .noteid(noteid)
                .build();
    }
}
