package live.baize.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import live.baize.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

}
