package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import start17.Memento.model.dto.NoteSaveRequestDto;
import start17.Memento.domain.note.NoteRepository;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Transactional
    public Long save(NoteSaveRequestDto requestDto){
        return noteRepository.save(requestDto.toEntity()).getId();
    }
}
