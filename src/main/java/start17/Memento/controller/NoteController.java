package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.note.Note;
import start17.Memento.model.dto.note.NoteResponseDto;
import start17.Memento.model.dto.note.NoteSaveRequestDto;
import start17.Memento.model.dto.note.NoteUpdateRequestDto;
import start17.Memento.service.NoteService;

import java.util.List;
@Api(tags="NoteBasic")
@RequiredArgsConstructor
@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    // 노트 등록
    @ApiOperation(value ="노트 등록", notes= "등록하고자 하는 note객체를 전달받아 db에 저장")
    @PostMapping("/add")
    public Long save(@RequestBody NoteSaveRequestDto requestDto){
        return noteService.save(requestDto);
    }

    //노트 수정
    @ApiOperation(value ="노트 수정", notes= "수정된 note객체 및 해당 노트의 id를 전달받아 db에 저장")
    @PutMapping("/{id}/edit")
    public Long update(@PathVariable Long id, @RequestBody NoteUpdateRequestDto requestDto){
        return noteService.update(id, requestDto);
    }
    //노트 조회

    @ApiOperation(value ="특정 노트 조회", notes= "특정 id의 노트를 조회")
    @GetMapping("/{id}")
    public NoteResponseDto findById (@PathVariable Long id){
        return noteService.findById(id);
    }

    @ApiOperation(value ="모든 노트 조회", notes= "저장된 모든 노트를 조회")
    @GetMapping("/list")
    public List<Note> findAll (){
        return noteService.findAll();
    }

    @ApiOperation(value ="노트 전체 삭제", notes= "등록된 노트 모두를 삭제")
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
    }

    @ApiOperation(value ="특정 노트 삭제", notes= "등록된 특정 노트 하나를 삭제")
    @DeleteMapping("/deleteAll")
    public void delete(){
    }
}
