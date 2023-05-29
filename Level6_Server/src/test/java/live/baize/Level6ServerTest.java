package live.baize;

import live.baize.entity.Paper;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class Level6ServerTest {

    @Test
    void contextLoads() {
//        Calendar Cal = Calendar.getInstance();
//        Cal.setTime(new Date());
//        Cal.add(Calendar.MINUTE, 135);
//        Date time = Cal.getTime();
//        System.out.println("date:" + time);


        Comparator.comparingInt(Paper::getQuestionId);
    }

    @Test
    void questions() {
        //  BCABADDCCADDACBCBDCABADABKGLHBJAINDDGCEHKFMBJACABCADBCD
    }
}
