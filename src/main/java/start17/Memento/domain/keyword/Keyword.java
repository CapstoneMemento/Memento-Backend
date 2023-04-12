package start17.Memento.domain.keyword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import start17.Memento.domain.note.Note;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@NamedQuery(
        name="Keyword.findByNoteid",
        query="select k from Keyword k where k.noteid = :noteid")

public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Long noteid;  // 외래키 X 일반 값으로 넣어줌 (entity생성 시 너무 복잡해짐 ㅠㅠ)

    @Column
    int first; // 시작인덱스

    @Column
    int last; //종결 인덱스

    public Keyword (Long noteid, int first, int last) {
        this.noteid = noteid;
        this.first = first;
        this.last = last;
    }


}
