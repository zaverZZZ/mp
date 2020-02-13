package com.zaver.mp.utils;

import java.util.Random;

/**
 * @ClassName : StringUtils
 * @Description TODO
 * @Date : 2019/4/16 20:51
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static boolean isNull(String str){
        return str==null||"".equals(str);
    }
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取一定长度的随机字符串
     * @param length  指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getIntegerString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 指定长度 获取数字字符串
     * @param length
     * @return
     */
    public static String getInteger(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 指定长度 获取英文字符串
     * @param length
     * @return
     */
    public static String getString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 10进制转36进制 0为A,1为B 以此类推(避免首位为0的情况);
     * 制定长度返回,位数不足补0(即补A)
     * @param length
     * @param num
     * @return
     */
    public static String tenTo36(int length,int num) {
        int radix = 36;
        char buf[] = new char[33];
        boolean negative = (num < 0);
        int charPos = 32;

        if (!negative) {
            num = -num;
        }

        while (num <= -radix) {
            buf[charPos--] = digits[-(num % radix)];
            num = num / radix;
        }
        buf[charPos] = digits[-num];

        if (negative) {
            buf[--charPos] = '-';
        }
        String string36 = new String(buf, charPos, (33 - charPos)).toUpperCase();
        if(string36.length()<length){
            string36 = getMoreString("A",length-string36.length())+string36;
        }
        return string36;
    }

    /**
     * 返回指定长度的指定字符串连接
     * @param s
     * @param length
     * @return
     */
    public static String getMoreString(String s,int length){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++){
            sb.append(s);
        }
        return sb.toString().toUpperCase();
    }



    final static char[] digits = {
            'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z' ,
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9'
    };
}
