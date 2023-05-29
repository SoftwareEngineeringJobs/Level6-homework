package live.baize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.baize.dao.RegistrationMapper;
import live.baize.entity.Registration;
import live.baize.service.RegistrationService;
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
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements RegistrationService {

}
