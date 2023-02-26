package start17.Memento.controller.search;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.domain.search.CaseInfo;
import start17.Memento.service.search.SearchWithQueryService;

import java.util.List;

@RestController

public class SearchWithQueryController {
    private final SearchWithQueryService searchService;

    public SearchWithQueryController(SearchWithQueryService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search")
    public  List<CaseInfo> searchWithQuery(@RequestParam String query) throws Exception{
        List<CaseInfo> caseInfoList = searchService.getCasesList(query);
        return caseInfoList;
    }


}
