package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;
    private Date registerTime;
    private Date testTime;
    private Date scoreTime;
    private Integer paperA;
    private Integer paperB;
    private Integer paperC;

    public Exam(Date registerTime, Date testTime, Date scoreTime,
                Integer paperA, Integer paperB, Integer paperC) {
        this.examId = 0;
        this.registerTime = registerTime;
        this.testTime = testTime;
        this.scoreTime = scoreTime;
        this.paperA = paperA;
        this.paperB = paperB;
        this.paperC = paperC;
    }

}
