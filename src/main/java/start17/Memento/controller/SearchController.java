package start17.Memento.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.service.search.CaseInfo;
import start17.Memento.service.search.SearchService;

import java.util.List;

@RestController

public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search")
    public  List<CaseInfo> search() throws Exception{
        List<CaseInfo> caseInfoList = searchService.getCasesDatas();
        return caseInfoList;
    }


}
