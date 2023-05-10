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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;
    private String password;
    private String name;
    private Boolean gender;
    private String email;
    private Integer authority;
}
