package com.boot.box;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2020/10/22 12:12 下午
 * @description
 */
public class CharTest {
    public static void main(String[] args){
        String str="€😘jekj，fj€dks@";//笑脸4个字节
        String str2="⏰kjsfjfjfj";//闹钟三个字节
        String str3="€";//三个字节
        String checkStr = "制片人：吴文芳\n导演：儿岛理华\n监制：儿岛理华 刘弘毅\n策划：刘瑜 叶文俊 儿岛理华 驰骋 赵天敏\n文案：驰骋 儿岛理华 徐洁婷 陈桂芬\n摄影：儿岛理华 杨海珊 李雨泽 陈诚 邓子明 刘弘毅\n剪辑：梁𣌀彤 杨海珊 李雨泽 梁嘉汶 刘弘毅 黄恒健\n美术指导：陈伟忠 陈伟良 吴艺忠\n设计：areyouserious 郑靖霖\n";
        String s = "曦";
        System.out.println(isContainSpecialChar(str));
        System.out.println(isContainSpecialChar(str2));
        System.out.println(isContainSpecialChar(str3));
        System.out.println(isContainSpecialChar(checkStr));
        System.out.println(isContainSpecialChar(s));

        }

    public static boolean isStartWithOver3Byte(byte b) {
        int unsignByte = Byte.toUnsignedInt(b);
        return (0xf0 <= unsignByte && unsignByte <= 0xf7) ||//4字节开头
                (0xf8 <= unsignByte && unsignByte <= 0xfb) ||//5字节开头
                (0xfc <= unsignByte && unsignByte <= 0xfd);//6字节开头
    }

    public static boolean isContainSpecialChar(String str) {
        if(StringUtils.isEmpty(str)){
            return false;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                if(isStartWithOver3Byte(bytes[i])){
                    return true;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;

    }

}

