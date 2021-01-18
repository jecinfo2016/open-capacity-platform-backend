package com.open.capacity.oss.utils;

import com.open.capacity.oss.md2html.toc.TocConstant;
import com.open.capacity.oss.model.CaLog;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 根据md文件中的#返回目录结构
 */
public class CatalogUtil {


    private CatalogUtil(){}

    /**
     * 空白字符串
     */
    public static final String EMPTY = "";
    /**
     * 空格
     * @since 1.0.5
     */
    public static final String BLANK = " ";

    /**
     * 是否为空
     * @param string 字符串
     * @return {@code true} 是
     */
    public static boolean isEmpty(final String string) {
        return null == string
                || string.equals(EMPTY);
    }

    public static CaLog mdCatalog(String content){
        int one = 0;
        int two = 0;
        int three = 0;
        CaLog caLog = new CaLog();
        caLog.setCatalog("目录");
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        ArrayList<String> strings2 = new ArrayList<>();
        String[] split = content.split("\n");
        for (String s : split) {
            // 定义一个子串
            String s1 = "#";
            // 统计次数
            int count = 0;
            while (true) {
                // 返回第一次出现子串的索引
                int index = s.indexOf(s1);
                if (index != -1) {
                    s = s.substring(index + s1.length());
                    count++;
                } else {
                    break;
                }
            }
            if (count == 1) {
                one++;
                StringBuffer stringBuffer = new StringBuffer(s);
                s = stringBuffer.insert(1, one + ".").toString();
                strings.add(s);
                two = 0;
            }
            if (count == 2) {
                two++;
                s = one + "." + two + "." + s;
                strings1.add(s);
                three = 0;
            }
            if (count == 3) {
                three++;
                StringBuffer stringBuffer = new StringBuffer(s);
                s = stringBuffer.insert(1, one + "." + two + "." + three + ".").toString();
                strings2.add(s);
            }
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings1) {
            String key = str.substring(0, str.indexOf(".")).trim();
            List<String> list = map.get(key);
            //如果map中对应key,创建String数组，并加到map中
            if (list == null || list.isEmpty()) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            //如果map有key对应的string数组，更新数组，为啥map没有put就更新了数组
            list.add(str);
        }
        Map<String, List<String>> map1 = new HashMap<>();
        for (String str : strings2) {
            int i = str.indexOf(".");
            String key = str.substring(0, str.indexOf(".", i + 1)).trim();
            List<String> list = map1.get(key);
            //如果map中对应key,创建String数组，并加到map中
            if (list == null || list.isEmpty()) {
                list = new ArrayList<>();
                map1.put(key, list);
            }
            //如果map有key对应的string数组，更新数组
            list.add(str);
        }
        //一级目录结构
        for (String a : strings) {
            CaLog caLog1 = new CaLog();
            caLog1.setCatalog(a);
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                List<String> value = entry.getValue();
                //二级目录结构
                value.forEach(b -> {
                    if (a.substring(0, a.indexOf(".")).trim().equals(b.substring(0, b.indexOf(".")).trim())) {
                        CaLog caLog2 = new CaLog();
                        caLog2.setCatalog(b);
                        caLog1.add(caLog2);
                        //三級目录结构
                        for (Map.Entry<String, List<String>> entry1 : map1.entrySet()) {
                            List<String> value1 = entry1.getValue();
                            value1.forEach(c -> {
                                int i = c.indexOf(".");
                                int i1 = b.indexOf(".");
                                if (b.substring(0, b.indexOf(".", i1 + 1)).trim().equals(c.substring(0, c.indexOf(".", i + 1)).trim())) {
                                    CaLog caLog3 = new CaLog();
                                    caLog3.setCatalog(c);
                                    caLog2.add(caLog3);
                                }
                            });
                        }
                    }
                });
            }
            caLog.add(caLog1);
        }
        return caLog;
    }



    /**
     * 标点符号正则
     * @since 1.0.8
     */
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{P}");

    /**
     * emoji 表情正则表达式
     * https://github.com/zly394/EmojiRegex
     * https://github.com/vdurmont/emoji-java
     * @since 1.0.8
     */
    private static final String EMOJI_PATTERN_STR = "(?:[\\uD83C\\uDF00-\\uD83D\\uDDFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDE4F]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u26FF]\\uFE0F?|[\\u2700-\\u27BF]\\uFE0F?|\\u24C2\\uFE0F?|[\\uD83C\\uDDE6-\\uD83C\\uDDFF]{1,2}|[\\uD83C\\uDD70\\uD83C\\uDD71\\uD83C\\uDD7E\\uD83C\\uDD7F\\uD83C\\uDD8E\\uD83C\\uDD91-\\uD83C\\uDD9A]\\uFE0F?|[\\u0023\\u002A\\u0030-\\u0039]\\uFE0F?\\u20E3|[\\u2194-\\u2199\\u21A9-\\u21AA]\\uFE0F?|[\\u2B05-\\u2B07\\u2B1B\\u2B1C\\u2B50\\u2B55]\\uFE0F?|[\\u2934\\u2935]\\uFE0F?|[\\u3030\\u303D]\\uFE0F?|[\\u3297\\u3299]\\uFE0F?|[\\uD83C\\uDE01\\uD83C\\uDE02\\uD83C\\uDE1A\\uD83C\\uDE2F\\uD83C\\uDE32-\\uD83C\\uDE3A\\uD83C\\uDE50\\uD83C\\uDE51]\\uFE0F?|[\\u203C\\u2049]\\uFE0F?|[\\u25AA\\u25AB\\u25B6\\u25C0\\u25FB-\\u25FE]\\uFE0F?|[\\u00A9\\u00AE]\\uFE0F?|[\\u2122\\u2139]\\uFE0F?|\\uD83C\\uDC04\\uFE0F?|\\uD83C\\uDCCF\\uFE0F?|[\\u231A\\u231B\\u2328\\u23CF\\u23E9-\\u23F3\\u23F8-\\u23FA]\\uFE0F?)";

    /**
     * 特殊符号
     */
    private static final String SPECIAL_CHARS = "≠≡⁄≤≥«#©¨!¯&®'$¬%£*+¢¡(≈)§./¦¥,»¸¾¿¼½;:°±¶?·>=´<@♠←↑→♣↓↔♥∩♦∫–—‚’×‘^„”◊“†‡•∂€…∏™‰″′˜−∑‹∞÷~|›√‾￥`️";

    /**
     * 过滤特殊字符
     * @param string 待验证字符
     * @return 结果
     */
    public static String filterSpecial(String string) {
        if(CatalogUtil.isEmpty(string)) {
            return string;
        }

        // 替换掉表情
        // github 部分表情是不一致的，暂时不做处理。
        String trimEmoji = string.replaceAll(EMOJI_PATTERN_STR, CatalogUtil.EMPTY);

        // 处理每一个字节
        char[] chars = trimEmoji.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : chars) {
            String cStr = String.valueOf(c);
            //1.-_ 直接添加
            if(TocConstant.MINUS.equals(cStr)
                    || TocConstant.UNDERLINE.equals(cStr)) {
                stringBuilder.append(c);
                continue;
            }
            //2. 特殊字符
            if(isSpecialChar(cStr)
                    || isPunctuation(cStr)) {
                continue;
            }
            //3. 其他
            stringBuilder.append(c);
        }

        // 过滤掉 pattern
        return stringBuilder.toString();
    }

    /**
     * 是否为特殊字符
     * （2）正则匹配的标点符号
     * （3）emoji 符号
     * （4）其他特殊符号
     * 统一放在此处判断
     * @return 是否
     * @since 1.0.8
     */
    private static boolean isSpecialChar(final String string) {
        return SPECIAL_CHARS.contains(string);
    }

    /**
     * 是否为标点符号
     * 中文符号：参考：https://blog.csdn.net/ztf312/article/details/54310542
     * @param string 字符
     * @return 结果
     */
    private static boolean isPunctuation(String string) {
        return PUNCTUATION_PATTERN.matcher(string).find();
    }

    /**
     * 是否为空
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection
                || 0 == collection.size();
    }


    /**
     * @since 1.0.5
     * @param objects 对象
     * @param separator 分隔符
     * @return 拼接的结果
     */
    public static String join(final List<?> objects, final String separator) {
        if(CatalogUtil.isEmpty(objects)) {
            return CatalogUtil.EMPTY;
        }

        //null 使用空格代替  避免NPE，也让根据 _ 分割时不存在问题。
        final String firstStr = objects.get(0).toString();
        StringBuilder stringBuilder = new StringBuilder(firstStr);
        for(int i = 1; i < objects.size(); i++){
            String actualStr = objects.get(i).toString();
            stringBuilder.append(separator).append(actualStr);
        }
        return stringBuilder.toString();
    }
}
