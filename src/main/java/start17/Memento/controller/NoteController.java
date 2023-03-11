package start17.Memento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.note.Note;
import start17.Memento.model.dto.note.NoteResponseDto;
import start17.Memento.model.dto.note.NoteSaveRequestDto;
import start17.Memento.model.dto.note.NoteUpdateRequestDto;
import start17.Memento.service.NoteService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    // 노트 등록
    @PostMapping("/add")
    public Long save(@RequestBody NoteSaveRequestDto requestDto){
        return noteService.save(requestDto);
    }

    //노트 수정
    @PutMapping("/{id}/edit")
    public Long update(@PathVariable Long id, @RequestBody NoteUpdateRequestDto requestDto){
        return noteService.update(id, requestDto);
    }
    //노트 조회

    @GetMapping("/{id}")
    public NoteResponseDto findById (@PathVariable Long id){
        return noteService.findById(id);
    }

    @GetMapping("/list")
    public List<Note> findAll (){
        return noteService.findAll();
    }
    @DeleteMapping("/deleteAll")
    public void delete(){
    }
}
