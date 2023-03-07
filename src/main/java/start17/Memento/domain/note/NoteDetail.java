package start17.Memento.domain.note;

import java.util.Date;

public class NoteDetail {
    private int notes_id; // 대상 노트 id
    private Date date; //생성날짜
    private String casenum; //사건번호
    private String court; // 법원 종류
    private String judge_type; // 판결 종류 (ex: 결정, 판결)
    private String url; //판례 상세 링크
}
