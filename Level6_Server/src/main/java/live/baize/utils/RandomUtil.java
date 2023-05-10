package live.baize.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private static final String String_Seed_ASCII = "!\"#$%&'()*+,-./0123456789:;<=>?@abcdefghijklmnopqrstuvwxyz[\\]^_`ABCDEFGHIJKLMNOPQRSTUVWXYZ{|}~";
    private static final String String_Seed_Number = "0123456789";

    private static String generateRandomString(String StringSeed, int size) {
        Random random = new Random();
        char[] chars = (StringSeed).toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            int num = random.nextInt(chars.length);
            char c = chars[num];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * 生成验证码
     *
     * @return String 6位数字验证码
     */
    public static String generateVerifyCode() {
        return generateRandomString(String_Seed_Number, 6);
    }

    /**
     * 生成密码盐
     *
     * @return String 16位随机密码盐
     */
    public static String generatePasswdSalt() {
        return generateRandomString(String_Seed_ASCII, 16);
    }

    /**
     * 生成UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
