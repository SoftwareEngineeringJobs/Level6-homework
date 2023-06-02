package live.baize.utils;

import live.baize.dto.ResponseEnum;
import live.baize.exception.SystemException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswdUtil {
    private static final String SHA_128 = "SHA-1";
    private static final String SHA_256 = "SHA-256";
    private static final String AES = "AES";
    // 密钥 应当特殊保存
    private static final String AES_KEY = "5oiR54ix5byg5byV";

    private static String getEncryptStr(String algorithm, String str) {
        MessageDigest messageDigest;
        String encryptStr;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
            byte[] hash = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            encryptStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(ResponseEnum.SYSTEM_UNKNOWN, e);
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
    public static String generatePassword(String password) {
        return getEncryptStr(SHA_256, password);
    }

    /**
     * AES 加密
     */
    public static String encryptAES(String src) {
        byte[] raw = AES_KEY.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(raw, AES);
        String encryptStr;
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(src.getBytes());
            encryptStr = Hex.encodeHexString(encrypted);
        } catch (Exception e) {
            throw new SystemException(ResponseEnum.SYSTEM_UNKNOWN, e);
        }
        return encryptStr;
    }

    /**
     * AES 解密
     */
    public static String decryptAES(String src) {
        byte[] raw = AES_KEY.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(raw, AES);
        String decryptStr;
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decrypted = cipher.doFinal(Hex.decodeHex(src));
            decryptStr = new String(decrypted);
        } catch (Exception e) {
            throw new SystemException(ResponseEnum.SYSTEM_UNKNOWN, e);
        }
        return decryptStr;
    }

}
