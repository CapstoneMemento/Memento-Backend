package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags="Quiz")
@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    @ApiOperation(value ="퀴즈 리스트 가져오기", notes= "입력받은 카테고리 id에 대해 한글 배열로 된 키워드 반환")
    @GetMapping("/{categories_id}")
    public List<Quiz> getQuizList (@PathVariable int categories_id){
        return quizService.getQuizList(categories_id);
    }
}
