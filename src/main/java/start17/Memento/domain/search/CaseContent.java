package start17.Memento.domain.search;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
public class CaseContent {
    private String title;
    private String sentence;
    private String main;
    private String provision;
    private String reason;
}
