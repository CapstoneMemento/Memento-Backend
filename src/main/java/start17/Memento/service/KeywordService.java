package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import start17.Memento.domain.keyword.Keyword;
import start17.Memento.domain.keyword.KeywordRepository;
import start17.Memento.model.dto.keyword.KeywordSaveRequestDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    @Transactional
    public List<Keyword> save(List<KeywordSaveRequestDto> requestDtos){
        for (int i = 0; i< requestDtos.size(); i++){
            keywordRepository.save(requestDtos.get(i).toEntity());
        }
        return keywordRepository.findByNoteid(requestDtos.get(0).getNoteid()); // 해당 노트 id로 저장된 키워드 들 가져옴
    }
    @Transactional
    public void deleteAll() {
        keywordRepository.deleteAll();
    }

    public List<Keyword> findAll (){
        return keywordRepository.findAll();
    }
}
