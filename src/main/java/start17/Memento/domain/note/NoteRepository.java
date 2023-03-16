package start17.Memento.domain.note;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoteRepository {
    private static final Map<Long, Note> store = new HashMap<>();
    private static long sequence = 0L;

    public Note save(Note note) {
        note.setId(++sequence);
        store.put(note.getId(),note);
        return note;
    }

    public Note findById (Long id){
        return store.get(id);
    }

    public List<Note> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update (Long noteId, Note updateParam){
        Note findNote = findById(noteId);
        findNote.setTitle(updateParam.getTitle());
        findNote.setContent(updateParam.getContent());
        findNote.setCategory(updateParam.getCategory());
        findNote.setSave_type(updateParam.getSave_type());
    }

    public void clearStore(){
        store.clear();
    }
}
