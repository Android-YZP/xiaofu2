package cn.company1075.xiaofu.utils.xiaofu;

/**
 * 字符串综合
 */
public class StringUtils {

    /**
     * @param string 是否是非空字符且不是空字符串
     */
    public static boolean StringNotNull(String string) {
        return string != null && !string.equals("");
    }

}
