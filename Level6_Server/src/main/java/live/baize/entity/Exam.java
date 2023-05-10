package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
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
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;
    private Date examTime;
    private Integer paperA;
    private Integer paperB;
    private Integer paperC;
}
