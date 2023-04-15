package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.keyword.Keyword;
import start17.Memento.model.dto.keyword.KeywordSaveRequestDto;
import start17.Memento.service.KeywordService;

import java.util.List;

@Api(tags="Keyword")
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

    @ApiOperation(value ="키워드 전체 삭제", notes= "등록된 키워드 모두를 삭제")
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        keywordService.deleteAll();
    }

    @ApiOperation (value = "특정 노트 키워드 전체 삭제", notes = "특정 노트의 키워드 모두를 삭제")
    @DeleteMapping("/deleteBynoteid/{noteid}")
    public void deleteByNoteid(@PathVariable Long noteid){
        keywordService.deleteByNoteid(noteid);
    }
    @ApiOperation(value ="키워드 전체 조회", notes= "등록된 키워드 모두를 조회")
    @DeleteMapping("/findAll")
    public void findAll(){
        keywordService.findAll();
    }

    @ApiOperation(value ="특정 노트 키워드 조회", notes= "해당 아이디의 키워드 리스트 조회")
    @GetMapping("/{noteid}")
    public List<Keyword> findKeyword(@PathVariable Long noteid){
        return keywordService.findByNoteid(noteid);
    }

    @ApiOperation(value = "특정 노트 키워드 수정", notes = "해당 노트 아이디의 키워드 수정")
    @PostMapping("/edit")
    public void updateKeyword (@RequestBody List<KeywordSaveRequestDto> requestDto){
        keywordService.UpdateKeyword(requestDto);
    }

}
