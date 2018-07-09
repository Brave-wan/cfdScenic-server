package com.htrj.common.utils;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import org.apache.commons.lang.StringUtils;

/**
 * 将汉字转换为拼音
 * @author Hejue
 */
public class PinyinHelper {

    /**
     * 返回一个汉字的拼音
     */
    public static String getCharPinyin(char ch) {
        HanyuPinyinOutputFormat format = getDefaultOutputFormat();

        String[] py = null;
        try {
            py = net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(ch, format);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 当转换不是中文字符时,返回null
        return (py == null) ? null : py[0];
    }

    /**
     * 获得一个字符串的汉语拼音
     */
    public static String getPinyin(String chinese) {
        if (StringUtils.isBlank(chinese))
            return chinese;

        StringBuilder pinyin = new StringBuilder();

        for (int i = 0; i < chinese.length(); ++i) {
            String py = getCharPinyin(chinese.charAt(i));

            // 当转换不是中文字符时,返回null
            if (py != null) {
                pinyin.append(upperCaseFirst(py));
            } else {
                pinyin.append(chinese.charAt(i));
            }
        }

        return pinyin.toString();
    }

    /**
     * 获得一个字符串的汉语拼音首字母
     */
    public static String getFirstPinyin(String chinese) {
        if (StringUtils.isBlank(chinese))
            return chinese;

        StringBuilder pinyin = new StringBuilder();

        for (int i = 0; i < chinese.length(); ++i) {
            String py = getCharPinyin(chinese.charAt(i));

            // 当转换不是中文字符时,返回null
            if (py != null) {
                pinyin.append(Character.toUpperCase(py.charAt(0)));
            } else {
                pinyin.append(chinese.charAt(i));
            }
        }

        return pinyin.toString();
    }

    /**
     * 设置一个常用的输出格式
     */
    private static HanyuPinyinOutputFormat getDefaultOutputFormat() {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// u显示
        return format;
    }

    /**
     * 首字母转大写
     */
    public static String upperCaseFirst(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static void main(String[] args) {
        String cn = "hello!";
        String cn1 = PinyinHelper.getPinyin(cn);
        System.out.println(cn1);
        String cn2 = PinyinHelper.upperCaseFirst(cn);
        System.out.println(cn2);
    }
}
