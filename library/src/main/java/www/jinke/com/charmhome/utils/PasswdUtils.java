package www.jinke.com.charmhome.utils;

/**
 * Created by root on 17-12-18.
 */

public class PasswdUtils {
    /**
     * 不能全是相同的数字或者字母（如：000000、111111、aaaaaa）
     * @return 全部相同返回true
     */
    public static boolean equalStr(String numOrStr) {
        boolean flag = true;
        char str = numOrStr.charAt(0);
        for (int i = 0; i < numOrStr.length(); i++) {
            if (str != numOrStr.charAt(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 不能是连续的数字--递增（如：123456、12345678）
     *
     * @param numOrStr
     * @return 连续数字返回true
     */
    public static boolean isOrderNumeric(String numOrStr) {
        boolean flag = true;//如果全是连续数字返回true
        boolean isNumeric = true;//如果全是数字返回true
        for (int i = 0; i < numOrStr.length(); i++) {
            if (!Character.isDigit(numOrStr.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        if (isNumeric) {//如果全是数字则执行是否连续数字判断
            for (int i = 0; i < numOrStr.length(); i++) {
                if (i > 0) {//判断如123456
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 不能是连续的数字--递减（如：987654、876543）
     * @param numOrStr
     * @return 连续数字返回true
     */
    public static boolean isOrderNumeric_(String numOrStr) {
        boolean flag = true;//如果全是连续数字返回true
        boolean isNumeric = true;//如果全是数字返回true
        for (int i = 0; i < numOrStr.length(); i++) {
            if (!Character.isDigit(numOrStr.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        if (isNumeric) {//如果全是数字则执行是否连续数字判断
            for (int i = 0; i < numOrStr.length(); i++) {
                if (i > 0) {//判断如654321
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") - 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }
}
