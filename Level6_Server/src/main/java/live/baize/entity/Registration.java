package live.baize.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "registration_id", type = IdType.AUTO)
    private Integer registrationId;
    private Integer examId;
    private Integer stuId;
    private Date registerTime;
    private Boolean paid;
    private Integer score;
}
