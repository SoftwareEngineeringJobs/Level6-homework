package live.baize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.baize.dao.StudentMapper;
import live.baize.entity.Student;
import live.baize.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
