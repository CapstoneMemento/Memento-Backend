package start17.Memento.domain.search;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CaseInfo {
    private int number; //식별자
    private String name; //판례 제목
    private String casenum; //사건번호
    private Date date; //판결 날짜
    private String court; // 법원 종류
    private String type; //사건 종류 (ex: 민사, 형사)
    private String judge_type; // 판결 종류 (ex: 결정, 판결)
    private String url; //판례 상세 링크

    @Builder
    public CaseInfo(int number, String name, String casenum, Date date, String court, String type, String judge_type, String url) {
        this.number = number;
        this.name = name;
        this.casenum = casenum;
        this.date = date;
        this.court = court;
        this.type = type;
        this.judge_type = judge_type;
        this.url = url;
    }
}
