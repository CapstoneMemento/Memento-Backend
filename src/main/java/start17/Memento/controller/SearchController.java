package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.search.CaseContent;
import start17.Memento.domain.search.CaseInfo;
import start17.Memento.service.SearchService;

import java.io.IOException;
import java.util.List;

@Api(tags="Search")
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService ss;
    //검색어 (=query)입력 시 그에 따른 판례 목록 불러옴
    @GetMapping("/find")
    @ApiOperation(value ="판례 검색 결과", notes= "검색어 입력을 통한 판례목록 추출")
    public List<CaseInfo> searchWithQuery(@RequestParam String query) throws Exception{
        List<CaseInfo> caseInfoList = ss.getCasesListbyQuery(query);
        return caseInfoList;
    }

    // caseInfoList에 존재하는 caseInfo 클릭 시 해당 객체의 caseID를 받아 본문 불러옴 -> 아직 구현 X
    @GetMapping("/content")
    @ApiOperation(value = "판례 본문 화면", notes ="저장하고자 하는 판례 클릭 시 해당 판례ID를 받아 본문 불러옴")
    @ResponseBody
    public CaseContent searchResultShow(@RequestParam int caseid) throws IOException {
        CaseContent cc =ss.getCaseContent(caseid);
        return cc;
    }
}
