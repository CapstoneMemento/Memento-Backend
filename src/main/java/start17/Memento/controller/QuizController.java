package start17.Memento.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import start17.Memento.domain.quiz.QuestionForm;
import start17.Memento.domain.quiz.Result;
import start17.Memento.service.QuizService;

@Api(tags="quiz")
@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    Result result;
    @Autowired
    QuizService qService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult(){
        return result;
    }

    @PostMapping("/test")
    public String test(@RequestParam String username, Model m, RedirectAttributes ra){
        submitted=false;
        QuestionForm qForm = qService.getQuestions();
        m.addAttribute("qFrom", qForm);
        return "quiz.html";
    }

    //퀴즈 푼 후 결과화면
    @PostMapping("/result")
    public String testresult(@ModelAttribute QuestionForm qForm, Model m){
        if(!submitted){
            result.setTotalCorrect(qService.getResult(qForm));
            qService.saveScore(result);
            submitted=true;
        }
        return "result.html";
    }



}
