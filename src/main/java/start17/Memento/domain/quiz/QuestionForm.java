package start17.Memento.domain.quiz;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionForm {
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }
}

