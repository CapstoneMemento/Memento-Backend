package start17.Memento.domain.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Quiz {
    String title;
    List<String> keywords;

    @Builder
    public Quiz(String title, List<String>keywords){
        this.title = title;
        this.keywords = keywords;
    }
}
