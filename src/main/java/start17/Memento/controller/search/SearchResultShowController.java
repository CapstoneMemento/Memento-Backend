package start17.Memento.controller.search;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.domain.search.CaseContent;
import start17.Memento.service.search.SearchResultShowService;

import java.io.IOException;
@RestController
public class SearchResultShowController {

    //사용자가 클릭한 판례에 대해서 판례 본문 보여줌
    @RequestMapping("/search/show")
    @ResponseBody
    public CaseContent searchResultShow() throws IOException {
        SearchResultShowService srss = new SearchResultShowService();
        CaseContent cc =srss.getCaseContent();
        return cc;
    }
}
