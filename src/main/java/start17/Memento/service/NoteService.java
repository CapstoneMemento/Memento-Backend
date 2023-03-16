package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import start17.Memento.domain.note.Note;
import start17.Memento.model.dto.note.NoteResponseDto;
import start17.Memento.model.dto.note.NoteSaveRequestDto;
import start17.Memento.domain.note.NoteRepository;
import start17.Memento.model.dto.note.NoteUpdateRequestDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Transactional
    public Long save(NoteSaveRequestDto requestDto){
        return noteRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id, NoteUpdateRequestDto requestDto){
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 노트가 존재하지 않습니다. id =" + id));

        note.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public NoteResponseDto findById(Long id) {
        Note entity = noteRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("해당 노트가 존재하지 않습니다. id =" + id));
        return new NoteResponseDto(entity);
    }

    public List<Note> findAll(){
        List<Note> notelist = noteRepository.findAll();
        return notelist;
    }

    @Transactional
    public void deleteAll(){
        noteRepository.deleteAll();
    }
    @Transactional
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
}
