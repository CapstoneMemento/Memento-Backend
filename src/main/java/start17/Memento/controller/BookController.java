package start17.Memento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.model.dto.books;
import start17.Memento.service.TestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final TestService testService;

    @GetMapping("/books")
    public List<books> test() {
        return testService.getAllDataList();
    }
}
