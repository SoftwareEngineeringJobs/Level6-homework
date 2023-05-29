package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "registration_id", type = IdType.AUTO)
    private Integer registrationId;
    private Integer examId;
    private Integer paperId;
    private Integer stuId;
    private Date registerTime;
    private Boolean paid;
    private Integer scoreListen;
    private Integer scoreRead;
    private Integer scoreWrite;
    private String choice;
    private String writing;
    private String translation;
}
