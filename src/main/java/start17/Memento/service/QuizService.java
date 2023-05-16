package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import start17.Memento.domain.keyword.Keyword;
import start17.Memento.domain.keyword.KeywordRepository;
import start17.Memento.domain.note.Note;
import start17.Memento.domain.note.NoteRepository;
import start17.Memento.domain.quiz.Quiz;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final NoteRepository noteRepository;
    private final KeywordRepository keywordRepository;


    public List<Quiz> getQuizList(int categories_id,String userid){
        List<Quiz> quizlist = new ArrayList<>();
        Note n;

        //해당 카테고리 내 노트 리스트들 가져옴
        List<Note> notes = noteRepository.findByCategoryID(categories_id,userid);

        for(int i=0; i<notes.size();i++){
            n = notes.get(i);
            List<Keyword> indexes = keywordRepository.findByNoteid(n.getId());
            List<String> keywords = new ArrayList<>(); // 한글이 들어있는 키워드
            for (int j=0; j<indexes.size();j++){ // 한글 키워드 변환
                keywords.add(n.getContent().substring(indexes.get(j).getFirst(), indexes.get(j).getLast()));
            }
            Quiz q = Quiz.builder()
                    .title(n.getTitle())
                    .keywords(keywords)
                    .build();
            quizlist.add(q);
        }
        return quizlist;
    }

}
