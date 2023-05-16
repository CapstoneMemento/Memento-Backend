package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import start17.Memento.domain.note.Note;
import start17.Memento.model.dto.note.NoteResponseDto;
import start17.Memento.model.dto.note.NoteSaveRequestDto;
import start17.Memento.domain.note.NoteRepository;
import start17.Memento.model.dto.note.NoteUpdateRequestDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public List<Note> findAll(String userid){
        List<Note> notelist = noteRepository.findByUserID(userid);
        return notelist;
    }

    public List<Note> findByCategoryID(int categories_id, String userid){
        List<Note> quizlist = noteRepository.findByCategoryID(categories_id, userid);
        return quizlist;

    }

    @Transactional
    public void deleteAll(List<Note> notes){
        for(Note note : notes){
            Optional<Note> oNote = noteRepository.findById(note.getId());
            if(oNote.isPresent()){
                noteRepository.delete(oNote.get());
            }
        }
    }
    @Transactional
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
}
