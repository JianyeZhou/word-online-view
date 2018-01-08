package net.marscore.common;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author Eden
 */
public class CommonUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isChange(boolean status, boolean result) {
        if (result) {
            return true;
        }
        return status;
    }

    public static boolean isInLength(String str, int min, int max){
        if (str==null) {
            return false;
        }
        return (str.length()>=min && str.length()<=max);
    }

    public static boolean isInLength(String str, int max){
        return isInLength(str, 2, max);
    }

    public static boolean isInLengthIgnoreNull(String str, int min, int max){
        if (str==null || "".equals(str)) {
            return true;
        }
        return (str.length()>=min && str.length()<=max);
    }

    public static boolean isInLengthIgnoreNull(String str, int max){
        return isInLengthIgnoreNull(str, 2, max);
    }

    public static boolean isNumber(String str) {
        return Pattern.matches("^[0-9]*$", str);
    }

    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email);
    }

    public static long[] toLongArray(String[] strs) {
        long[] array = new long[strs.length];
        for(int i=0; i<strs.length; i++) {
            array[i] = Long.parseLong(strs[i]);
        }
        return array;
    }

    public static boolean isPhone(String phoneNum) {
        if (phoneNum == null) {
            return false;
        }
        return Pattern.matches("^1(\\d{10})$", phoneNum);
    }
    /**
     * 计算字符串的Md5值
     * @param s
     * @return
     */
    public final static String calculateMD5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算流的Md5值
     * @param inStream
     * @return
     */
    public final static String calculateMD5(InputStream inStream)
    {
        MessageDigest digest = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = inStream.read(buffer, 0, 1024)) != -1)
            {
                digest.update(buffer, 0, len);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 生成随机字符串
     * @param length 生成字符串的长度
     * @return 指定长度的随机字符串
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public String getPasswordMD5(String password, String passwordSalt) {
        return CommonUtil.calculateMD5(passwordSalt+password);
    }
}
