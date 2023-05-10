package live.baize.service.impl;

import live.baize.entity.File;
import live.baize.dao.FileMapper;
import live.baize.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
