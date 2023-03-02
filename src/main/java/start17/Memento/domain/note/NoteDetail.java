package start17.Memento.domain.note;

import java.util.Date;

public class NoteDetail {
    private int note_id; // 대상 노트 id
    private boolean playlist; //플레이리스트 포함 여부
    private boolean quiz; // 퀴즈 여부
    private Date date; //생성날짜
    private boolean wrong; //오답 여부
    private Integer scrap; // 스크랩수
    private String casenum; //사건번호
    private String court; // 법원 종류
    private String judge_type; // 판결 종류 (ex: 결정, 판결)
    private String url; //판례 상세 링크
}
