package start17.Memento.domain.note;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
//상세페이지에 변경 가능한 것들만 모아둠
public class Note {
    private long id;
    private String title; //노트 제목
    private String content; //노트 내용
    private String save_type; // 저장 타입 (ex: 두문자)
    private String category; // 포함 목차

    public Note(){}

    public Note(long id, String title, String content, String save_type, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.save_type = save_type;
        this.category = category;
    }

}
