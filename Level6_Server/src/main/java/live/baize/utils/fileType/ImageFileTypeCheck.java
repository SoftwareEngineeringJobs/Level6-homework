package live.baize.utils.fileType;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

public class ImageFileTypeCheck extends FileTypeCheck {

    final static HashSet<String> IMAGE_FILE_TYPE;

    static {
        IMAGE_FILE_TYPE = new HashSet<>();

        IMAGE_FILE_TYPE.add("jpg");
        IMAGE_FILE_TYPE.add("png");
        IMAGE_FILE_TYPE.add("jpeg");
    }

    public static String fileTypeCheck(MultipartFile file) {
        return fileTypeCheck(file, IMAGE_FILE_TYPE);
    }
}
