package start17.Memento.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import start17.Memento.domain.search.NoteForm;

@Controller
public class SearchResultSaveController {
    @GetMapping("/search/newform")
    public String newNoteForm(){
        return "search/newForm";
    }
    @PostMapping("/search/create")
    public String createNote(NoteForm form){
        System.out.println(form.toString());
        return "";
    }
}
