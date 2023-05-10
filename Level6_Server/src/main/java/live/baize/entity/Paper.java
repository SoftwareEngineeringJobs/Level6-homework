package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;
    private Integer article;
    private Integer question;
    private Integer answer;
    private Integer listening;
}
