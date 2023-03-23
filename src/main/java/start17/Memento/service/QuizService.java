package start17.Memento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start17.Memento.domain.quiz.Question;
import start17.Memento.domain.quiz.QuestionForm;

@Service
public class QuizService {
    @Autowired
    Question question;
    @Autowired
    QuestionForm qForm;
    @Autowired
    QuestionRepo


}
