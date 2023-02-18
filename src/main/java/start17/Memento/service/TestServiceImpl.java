package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import start17.Memento.model.dao.TestMapper;
import start17.Memento.model.dto.books;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{
    private final TestMapper testMapper;

    @Override
    public List<books> getAllDataList() {
        return testMapper.getAllDataList();
    }
}
