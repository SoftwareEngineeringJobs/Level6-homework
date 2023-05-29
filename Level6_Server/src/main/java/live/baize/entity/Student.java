package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "stu_id", type = IdType.AUTO)
    private Integer stuId;
    private String idCard;
    private String name;
    private Boolean gender;
    private String password;
    private String school;
    private Integer cet4;
    private Integer cet6;

}
