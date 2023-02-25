package start17.Memento.controller.search;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.service.search.CaseInfo;
import start17.Memento.service.search.SearchService;

import java.util.List;

@RestController

public class SearchWithQueryController {
    private final SearchService searchService;

    public SearchWithQueryController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search")
    public  List<CaseInfo> searchWithQuery(@RequestParam String query) throws Exception{
        List<CaseInfo> caseInfoList = searchService.getCasesDatas(query);
        return caseInfoList;
    }


}
