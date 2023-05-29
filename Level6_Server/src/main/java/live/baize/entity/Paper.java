package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;
    private Integer questionId;
    private String question;
    private String answer;

}
