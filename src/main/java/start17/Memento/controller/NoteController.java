package start17.Memento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import start17.Memento.model.dto.NoteSaveRequestDto;
import start17.Memento.service.NoteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/add")
    public Long save(@RequestBody NoteSaveRequestDto requestDto){
        return noteService.save(requestDto);
    }

    @PutMapping("/{id}")


}
