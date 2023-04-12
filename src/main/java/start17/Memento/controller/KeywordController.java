package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.keyword.Keyword;
import start17.Memento.model.dto.keyword.KeywordSaveRequestDto;
import start17.Memento.service.KeywordService;

import java.util.List;

@Api(tags="NoteBasic")
@RequiredArgsConstructor
@RestController
@RequestMapping("/keyword")
public class KeywordController {
    private final KeywordService keywordService;

    @ApiOperation(value ="키워드 인덱스 ", notes= "note 키워드 index를 담은 list를 전달받아 하나하나 keyword DB에 넣어줌")
    @PostMapping("/save")
    public List<Keyword> savekeyword (@RequestBody List<KeywordSaveRequestDto> requestDto){
        return keywordService.save(requestDto);
    }

    @ApiOperation(value ="노트 전체 삭제", notes= "등록된 노트 모두를 삭제")
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        keywordService.deleteAll();
    }
}
