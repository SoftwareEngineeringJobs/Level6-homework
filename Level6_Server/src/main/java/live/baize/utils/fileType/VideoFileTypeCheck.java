package live.baize.utils.fileType;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

@Service
public class VideoFileTypeCheck extends FileTypeCheck {

    final static HashSet<String> VIDEO_FILE_TYPE;

    static {
        VIDEO_FILE_TYPE = new HashSet<>();

        VIDEO_FILE_TYPE.add("mp4");
        VIDEO_FILE_TYPE.add("mov");
    }

    public static String fileTypeCheck(MultipartFile file) {
        return fileTypeCheck(file, VIDEO_FILE_TYPE);
    }
}
