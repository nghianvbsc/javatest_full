package bscenter.tracnghiemlaptrinh.model.objects;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by NIT Admin on 02/06/2016
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Question {
    private int id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String trueAnswer;
    private int level;

    public Question() {

    }
}
