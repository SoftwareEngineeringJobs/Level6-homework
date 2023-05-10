package live.baize.utils;

import live.baize.dto.ResponseEnum;
import live.baize.exception.SystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件第一种保存方式
 * 即 存储在本地
 */

@Service
@PropertySource("classpath:config.properties")
public class FileUtil {

    @Value("${file.saveDirectory}")
    private String fileDirectory;

    public void saveFile(String realPath, MultipartFile file) {
        File folder = new File(fileDirectory);

        if (!folder.exists() && !folder.mkdirs()) {
            throw new SystemException(ResponseEnum.Create_Dir_Failure);
        }

        try {
            file.transferTo(new File(folder, realPath));
        } catch (IOException e) {
            throw new SystemException(ResponseEnum.Write_File_Failure, e.getCause());
        }

    }

    public void deleteFile(String realPath) {
        File file = new File(fileDirectory + realPath);

        if (file.exists() && !file.delete()) {
            throw new SystemException(ResponseEnum.Delete_File_Failure);
        }
    }

}
