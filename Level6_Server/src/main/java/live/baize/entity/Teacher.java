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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;
    private String email;
    private String name;
    private Boolean gender;
    private String password;

    public Teacher (String email, String name, Boolean gender, String password) {
        this.teacherId = 0;     // 由数据库分配自增ID
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.password = password;
    }

    public void setPasswd (String password) {
        this.password = password;
    }
}
