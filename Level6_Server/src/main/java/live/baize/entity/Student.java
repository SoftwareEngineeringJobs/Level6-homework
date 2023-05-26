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

    @Override
    public String toString() {
        String result = "stu_id:" + stuId + ",id_card:" + idCard + ",name:" + name + "gender:";
        // 性别1表示女，0表示男
        if (gender)
            result += "女";
        else
            result += "男";
        result +=  ",password:" + password  + ",school:" + school + ",cet4:" + cet4 + ",cet6:" + cet6;
        return result;
    }
}
