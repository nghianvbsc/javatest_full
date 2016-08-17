package bscenter.tracnghiemlaptrinh.model.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by NIT Admin on 06/06/2016
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Answer {

    private boolean isTrueAnswer;
    private String content;

    public Answer() {

    }

    ;
}
