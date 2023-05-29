package live.baize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;
    private String password;
    private String name;
    private Boolean gender;
    private String email;
    private Integer authority;

    public Admin(String email, String name, Boolean gender, String password, Integer authority) {
        this.adminId = 0;   // 自增id，设置为0，由MySQL分配
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.authority = authority;
    }

}
