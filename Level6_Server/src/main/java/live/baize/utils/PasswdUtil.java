package live.baize.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswdUtil {
    private static final String SHA_128 = "SHA-1";
    private static final String SHA_256 = "SHA-256";

    private static String getEncryptStr(String algorithm, String str) {
        MessageDigest messageDigest;
        String encryptStr = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
            byte[] hash = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            encryptStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * 40 位
     */
    public static String generateFileName(String fileName) {
        return getEncryptStr(SHA_128, fileName);
    }

    /**
     * 64 位
     */
    public static String generatePassword(String password, String passwdSalt) {
        return getEncryptStr(SHA_256, password + passwdSalt);
    }
}
