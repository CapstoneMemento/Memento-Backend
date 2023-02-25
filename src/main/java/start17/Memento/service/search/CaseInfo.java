package start17.Memento.service.search;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CaseInfo {
    private int number;
    private String name;
    private String casenum;
    private Date date;
    private String court;
    private String type;
    private String judge_type;
    private String url;
}
