package start17.Memento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import start17.Memento.domain.note.Note;
import start17.Memento.domain.note.NoteRepository;

import java.util.List;

@RequestMapping("basic/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;

    //노트 목록
    @GetMapping
    public void notes(Model model){
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        //return /basic/notes
    }

    // 노트 상세페이지
    @GetMapping("/{noteId}")
    public void item(@PathVariable Long noteId, Model model){
        Note note = noteRepository.findById(noteId);
        model.addAttribute("note", note);
        //return basic/note
    }

    // 노트 등록 폼 불러오기
    @GetMapping("/add")
    public void addForm(){
        // return "basic/addForm"
    }

    //노트 등록
    @PostMapping("/add")
    public void addNote(Note note, RedirectAttributes redirectAttributes){
        Note savedNote = noteRepository.save(note);
        redirectAttributes.addAttribute("noteId", savedNote.getId());
        redirectAttributes.addAttribute("status", true);

        //그냥 뷰를 리턴하면 같은 값만 반복됨
        //return "redirect:/basic/notes/" + note.getId(); -> X (URL 인코딩 문제)
        // return "redirect:/basic/notes/{noteId}";

    }

    //노트 수정 폼 불러오기
    @GetMapping("{noteId}/edit")
    public void editFrom(@PathVariable Long noteId, Model model){
        Note note = noteRepository.findById(noteId);
        model.addAttribute("note", note);
        //return view
    }

    //노트 수정
    @GetMapping("{noteId}/edit")
    public void edit(@PathVariable Long noteId, @ModelAttribute Note note){
        noteRepository.update(noteId, note);
        //return redirect:/ -> 노트 상세페이지
    }
}
