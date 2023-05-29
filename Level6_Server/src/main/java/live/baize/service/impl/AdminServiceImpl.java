package live.baize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.baize.dao.AdminMapper;
import live.baize.entity.Admin;
import live.baize.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
