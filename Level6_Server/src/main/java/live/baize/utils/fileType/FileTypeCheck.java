package live.baize.utils.fileType;

import live.baize.dto.ResponseEnum;
import live.baize.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

public class FileTypeCheck {

    protected static String fileTypeCheck(MultipartFile file, HashSet<String> FILE_TYPE) {
        if (file == null) {
            throw new BusinessException(ResponseEnum.File_Is_Null);
        }
        String fileName = file.getOriginalFilename();

        if (fileName == null) {
            throw new BusinessException(ResponseEnum.FileName_Is_Null);
        }

        String[] contentType = fileName.split("\\.");
        if (contentType.length != 2 || !FILE_TYPE.contains(contentType[1])) {
            throw new BusinessException(ResponseEnum.File_Type_Error);
        }

        return contentType[1];
    }
}
