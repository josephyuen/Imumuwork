package com.nzf.mvpframe.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.ColorInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * 数据类型转换、单位转换
 *

 */
public class ConvertUtils {
    public static final long GB = 1073741824;
    public static final long MB = 1048576;
    public static final long KB = 1024;


    public static String toGbk(String str) {
        try {
            return new String(str.getBytes("utf-8"), "gbk");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return str;
        }
    }

    public static String toFileSizeString(long fileSize) {
        DecimalFormat df = new DecimalFormat("0.00");
        String fileSizeString;
        if (fileSize < KB) {
            fileSizeString = fileSize + "B";
        } else if (fileSize < MB) {
            fileSizeString = df.format((double) fileSize / KB) + "K";
        } else if (fileSize < GB) {
            fileSizeString = df.format((double) fileSize / MB) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / GB) + "G";
        }
        return fileSizeString;
    }

    public static String toString(InputStream is, String charset) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return sb.toString();
    }

    public static String toString(InputStream is) {
        return toString(is, "utf-8");
    }

    public static ShapeDrawable toRoundDrawable(@ColorInt int color, int radius) {
        float[] outerR = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape shape = new RoundRectShape(outerR, null, null);
        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.getPaint().setColor(color);
        return drawable;
    }

    public static StateListDrawable toStateRoundDrawable(@ColorInt int color, int radius) {
        return toStateListDrawable(new ColorDrawable(Color.TRANSPARENT), toRoundDrawable(color, radius));
    }

    public static StateListDrawable toStateRoundDrawable(@ColorInt int normalColor, @ColorInt int focusColor, int radius) {
        return toStateListDrawable(toRoundDrawable(normalColor, radius), toRoundDrawable(focusColor, radius));
    }

    /**
     * 对TextView、Button等设置不同状态时其文字颜色。
     * 参见：http://blog.csdn.net/sodino/article/details/6797821
     * Modified by liyujiang at 2015.08.13
     */
    public static ColorStateList toColorStateList(@ColorInt int normalColor, @ColorInt int pressedColor,
                                                  @ColorInt int focusedColor, @ColorInt int unableColor) {
        int[] colors = new int[]{pressedColor, focusedColor, normalColor, focusedColor, unableColor, normalColor};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    public static ColorStateList toColorStateList(@ColorInt int normalColor, @ColorInt int pressedColor) {
        return toColorStateList(normalColor, pressedColor, pressedColor, normalColor);
    }

    public static StateListDrawable toStateListDrawable(Drawable normal, Drawable pressed, Drawable focused, Drawable unable) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        drawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focused);
        drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
        drawable.addState(new int[]{android.R.attr.state_focused}, focused);
        drawable.addState(new int[]{android.R.attr.state_window_focused}, unable);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }

    public static StateListDrawable toStateListDrawable(@ColorInt int normalColor, @ColorInt int pressedColor,
                                                        @ColorInt int focusedColor, @ColorInt int unableColor) {
        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = new ColorDrawable(normalColor);
        Drawable pressed = new ColorDrawable(pressedColor);
        Drawable focused = new ColorDrawable(focusedColor);
        Drawable unable = new ColorDrawable(unableColor);
        drawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        drawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focused);
        drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
        drawable.addState(new int[]{android.R.attr.state_focused}, focused);
        drawable.addState(new int[]{android.R.attr.state_window_focused}, unable);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }

    public static StateListDrawable toStateListDrawable(Drawable normal, Drawable pressed) {
        return toStateListDrawable(normal, pressed, pressed, normal);
    }

    public static StateListDrawable toStateListDrawable(@ColorInt int normalColor, @ColorInt int pressedColor) {
        return toStateListDrawable(normalColor, pressedColor, pressedColor, normalColor);
    }

    public static StateListDrawable toStateListDrawable(@ColorInt int pressedColor) {
        return toStateListDrawable(Color.TRANSPARENT, pressedColor);
    }

}
