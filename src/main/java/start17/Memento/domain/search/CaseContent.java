package start17.Memento.domain.search;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
public class CaseContent {
    private String title; // 판례제목
    private String sentence; //판시사항
    private String main; //판결요지
    private String provision; //관련 조문
    private String reason; //판결 이유
}
