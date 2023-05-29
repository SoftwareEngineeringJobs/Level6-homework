package live.baize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.baize.dao.PaperMapper;
import live.baize.entity.Paper;
import live.baize.service.PaperService;
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
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

}
