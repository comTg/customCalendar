package top.vetoer.util;

import android.graphics.Paint;

/**
 * Created by tg on 18-1-21.
 * 文字相关处理类
 */

public class FontUtil {
    /**
     *
     * @param paint
     * @param str
     * @return  返回指定笔和指定字符串的长度
     */
    public static float getFontLength(Paint paint,String str){
        return paint.measureText(str);
    }

    /**
     *
     * @param paint
     * @return 返回指定笔的高度
     */
    public static float getFontHeight(Paint paint){
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent-fm.ascent;
    }

    /**
     *
     * @param paint
     * @return 返回指定笔离文字顶部的基准距离
     */
    public static float getFontLeading(Paint paint){
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.leading-fm.ascent;
    }

}
