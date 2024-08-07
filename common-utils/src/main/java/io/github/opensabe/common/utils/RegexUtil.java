package io.github.opensabe.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by houpu on 2017/7/26.
 */
public class RegexUtil {

    private static Pattern DOMAIN_PATTERN = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
    private static Pattern HOST_PATTERN = Pattern.compile("(http://|https://)?([^/]*)", Pattern.CASE_INSENSITIVE);

    public boolean isMatch(String regex, String value) {
        return Pattern.matches(regex, value);
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        String regex = "[\\w\\.\\-]+@\\w+\\.[a-zA-Z]+(\\.[a-zA-Z]+)*";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex, idCard);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinaMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    public static boolean checkKenyaMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
//        String regex = "^07(0[0-8]|[123][0-9]|[57][0-6]|6[45]|8[5-9]|9[0-2])\\d{6}$";
        String regex = "^07\\d{8}$";
        return Pattern.matches(regex, mobile);
    }

    public static boolean checkNigeriaMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        String regex = "^0?[1-9]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    public static boolean checkGhanaMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        String regex = "^0?\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
    public static boolean checkUgandaMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        String regex = "^0?\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    public static boolean checkMobile(String countryCode, String mobile) {
        if (countryCode == null || mobile == null) {
            return false;
        }
        switch (countryCode) {
            case "254":
                return checkKenyaMobile(mobile);
            case "234":
                return checkNigeriaMobile(mobile);
            case "233":
                return checkGhanaMobile(mobile);
            case "256":
                return checkUgandaMobile(mobile);
            default:
                return false;
        }
    }


    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *              数字之后是空格分隔的国家（地区）代码。</p>
     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

    /**
     * 匹配6~14位，包含字母与数字的字符串
     *
     * @param password 密码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPassword(String password) {
        String pattern = "^(?![0-9]+$)(?![a-zA-Z]+$).{6,14}$";
        return Pattern.matches(pattern, password);
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * <pre>
     * 获取网址 URL 的一级域
     * </pre>
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {

        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = DOMAIN_PATTERN.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return url;
    }

    public static String getHost(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        Matcher matcher = HOST_PATTERN.matcher(url);
        return matcher.find() ? matcher.group(2) : url;
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

    public static List<String> checkMobileAndGet(int operId, String message) {
        if (operId == 2) {
            return checkNigeriaMobileAndGet(message);
        } else if (operId == 3) {
            return checkGhanaMobileAndGet(message);
        }
        return Collections.emptyList();
    }

    public static List<String> checkNigeriaMobileAndGet(String message) {
        if (message == null) {
            return Collections.emptyList();
        }
        Matcher matcher = Pattern.compile("[1-9]\\d{9}").matcher(message);
        List<String> phoneList = new ArrayList<>();
        while (matcher.find()) {
            phoneList.add(matcher.group());
        }
        return phoneList;
    }

    public static List<String> checkGhanaMobileAndGet(String message) {
        if (message == null) {
            return Collections.emptyList();
        }
        Matcher matcher = Pattern.compile("[1-9]\\d{8}").matcher(message);
        List<String> phoneList = new ArrayList<>();
        while (matcher.find()) {
            phoneList.add(matcher.group());
        }
        return phoneList;
    }
    public static List<String> checkUgandaMobileAndGet(String message) {
        if (message == null) {
            return Collections.emptyList();
        }
        Matcher matcher = Pattern.compile("[1-9]\\d{8}").matcher(message);
        List<String> phoneList = new ArrayList<>();
        while (matcher.find()) {
            phoneList.add(matcher.group());
        }
        return phoneList;
    }
}
