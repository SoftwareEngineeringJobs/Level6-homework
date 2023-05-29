package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@NoArgsConstructor
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date testTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
