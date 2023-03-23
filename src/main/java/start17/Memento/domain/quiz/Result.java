package start17.Memento.domain.quiz;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="results")
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalCorrect = 0;

    public Result(){
        super();
    }

    public Result(int id, int totalCorrect){
        super();
        this.id=id;
        this.totalCorrect = totalCorrect;
    }


}
