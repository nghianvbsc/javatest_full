package bscenter.tracnghiemlaptrinh.model.objects;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

/**
 * Created by NIT Admin on 03/06/2016
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Table(name = "savetest")
@AllArgsConstructor(suppressConstructorProperties = true)
public class SaveTest extends SugarRecord{

    @Column(name = "level", notNull = true)
    private int level;
    @Column(name = "true_answer", notNull = true)
    private int trueAnswer;
    @Column(name = "count_questions", notNull = true)
    private int countQuestions;
    @Column(name = "score", notNull = true)
    private int score;
    @Column(name = "time_test", notNull = true)
    private int timeTest;
    @Column(name = "time_play", notNull = true)
    private String timePlay;
    @Column(name = "type", notNull = true)
    private int type; //1 - bai test sau cung || 2 - bai test co diem cao

    public SaveTest(){

    }
}
