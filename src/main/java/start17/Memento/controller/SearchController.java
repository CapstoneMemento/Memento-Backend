package start17.Memento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.search.CaseContent;
import start17.Memento.domain.search.CaseInfo;
import start17.Memento.service.search.SearchResultShowService;
import start17.Memento.service.search.SearchWithQueryService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    @GetMapping("/find")
    public List<CaseInfo> searchWithQuery(@RequestParam String query) throws Exception{
        SearchWithQueryService swqs = new SearchWithQueryService();
        List<CaseInfo> caseInfoList = swqs.getCasesList(query);
        return caseInfoList;
    }

    @GetMapping("/content")
    @ResponseBody
    public CaseContent searchResultShow() throws IOException {
        SearchResultShowService srss = new SearchResultShowService();
        CaseContent cc =srss.getCaseContent();
        return cc;
    }
}
