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

    public List<Keyword> findByNoteid (Long noteid) {
        return keywordRepository.findByNoteid(noteid);}
    @Transactional
    public void deleteByNoteid(Long noteid){
        keywordRepository.deleteByNoteid(noteid);
    }
    @Transactional
    public void UpdateKeyword (List<KeywordSaveRequestDto> requestDtos){
        Long noteid = requestDtos.get(0).getNoteid();
        keywordRepository.deleteByNoteid(noteid);
        for (int i = 0; i< requestDtos.size(); i++){
            keywordRepository.save(requestDtos.get(i).toEntity());
        }
    }
}
