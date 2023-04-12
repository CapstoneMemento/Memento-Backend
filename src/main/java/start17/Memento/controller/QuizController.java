package start17.Memento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.domain.note.Note;
import start17.Memento.domain.quiz.Quiz;
import start17.Memento.model.dto.note.NoteResponseDto;
import start17.Memento.service.NoteService;
import start17.Memento.service.QuizService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    @GetMapping("/{categories_id}")
    public List<Quiz> getQuizList (@PathVariable int categories_id){
        return quizService.getQuizList(categories_id);
    }
}
