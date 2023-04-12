package start17.Memento.domain.note;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Getter
@NoArgsConstructor
@Entity
@NamedQuery(
        name="Note.findByCategoryID",
        query="select n from Note n where n.categories_id = :categories_id")
//상세페이지에 변경 가능한 것들만 모아둠
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(100) default 'EMPTY'" , nullable = true)
    private String title; //노트 제목
    @Column(columnDefinition = "varchar(1000) default 'EMPTY'" )
    private String content; //노트 내용
    @Column (nullable = true)
    private String type; // 저장 타입 (ex: 두문자)
    @Column (nullable = true)
    private int categories_id; // 포함 목차

    @Builder
    public Note(String title, String content, String type, int categories_id){
        this.title = title;
        this.content= content;
        this.type = type;
        this.categories_id = categories_id;
    }

    public void update (String title, String content){
        this.title = title;
        this.content = content;
    }
}
