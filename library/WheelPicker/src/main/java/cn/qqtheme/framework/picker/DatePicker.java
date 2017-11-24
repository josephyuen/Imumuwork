package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * 日期选择器
 *
 * @author 李玉江[QQ :1032694760]
 * @since 2015/12/14
 */
public class DatePicker extends WheelPicker {
    public static final int YEAR_MONTH_DAY = 0;//年月日
    public static final int YEAR_MONTH = 1;//年月
    public static final int MONTH_DAY = 2;//月日
    private ArrayList<String> years = new ArrayList<String>();
    private ArrayList<String> months = new ArrayList<String>();
    private ArrayList<String> days = new ArrayList<String>();
    private OnDatePickListener onDatePickListener;
    private String yearLabel = "年", monthLabel = "月", dayLabel = "日";
    private int startYear = 2010, startMonth = 1, startDay = 1;
    private int endYear = 2020, endMonth = 12, endDay = 31;
    private int selectedYearIndex = 0, selectedMonthIndex = 0, selectedDayIndex = 0;
    private int mode = YEAR_MONTH_DAY;

    /**
     * 安卓开发应避免使用枚举类（enum），因为相比于静态常量enum会花费两倍以上的内存。
     * http://developer.android.com/training/articles/memory.html#Overhead
     */
    @IntDef(value = {YEAR_MONTH_DAY, YEAR_MONTH, MONTH_DAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public DatePicker(Activity activity) {
        this(activity, YEAR_MONTH_DAY);
    }

    @NonNull
    @Override
    protected View makeCenterView() {
        return null;
    }

    /**
     * @see #YEAR_MONTH_DAY
     * @see #YEAR_MONTH
     * @see #MONTH_DAY
     */
    public DatePicker(Activity activity, @Mode int mode) {
        super(activity);
        this.mode = mode;
    }

    /**
     * 设置年月日的单位
     */
    public void setLabel(String yearLabel, String monthLabel, String dayLabel) {
        this.yearLabel = yearLabel;
        this.monthLabel = monthLabel;
        this.dayLabel = dayLabel;
    }



    /**
     * 设置范围：开始的年月日
     */
    public void setRangeStart(int startYear, int startMonth, int startDay) {
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
    }

    /**
     * 设置范围：结束的年月日
     */
    public void setRangeEnd(int endYear, int endMonth, int endDay) {
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
    }

    /**
     * 设置范围：开始的年月日
     */
    public void setRangeStart(int startYearOrMonth, int startMonthOrDay) {
        if (mode == YEAR_MONTH_DAY) {
            throw new IllegalArgumentException();
        }
        if (mode == YEAR_MONTH) {
            this.startYear = startYearOrMonth;
            this.startMonth = startMonthOrDay;
        } else {
            int year = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
            startYear = endYear = year;
            this.startMonth = startYearOrMonth;
            this.startDay = startMonthOrDay;
        }
    }

    /**
     * 设置范围：结束的年月日
     */
    public void setRangeEnd(int endYearOrMonth, int endMonthOrDay) {
        if (mode == YEAR_MONTH_DAY) {
            throw new IllegalArgumentException();
        }
        if (mode == YEAR_MONTH) {
            this.endYear = endYearOrMonth;
            this.endMonth = endMonthOrDay;
        } else {
            this.endMonth = endYearOrMonth;
            this.endDay = endMonthOrDay;
        }
    }


    public void setOnDatePickListener(OnDatePickListener listener) {
        this.onDatePickListener = listener;
    }

    @Override
    protected void onSubmit() {
        if (onDatePickListener == null) {
            return;
        }
        String year = getSelectedYear();
        String month = getSelectedMonth();
        String day = getSelectedDay();
        switch (mode) {
            case YEAR_MONTH:
                ((OnYearMonthPickListener) onDatePickListener).onDatePicked(year, month);
                break;
            case MONTH_DAY:
                ((OnMonthDayPickListener) onDatePickListener).onDatePicked(month, day);
                break;
            default:
                ((OnYearMonthDayPickListener) onDatePickListener).onDatePicked(year, month, day);
                break;
        }
    }

    public String getSelectedYear() {
        return years.get(selectedYearIndex);
    }

    public String getSelectedMonth() {
        return months.get(selectedMonthIndex);
    }

    public String getSelectedDay() {
        return days.get(selectedDayIndex);
    }

    protected interface OnDatePickListener {

    }

    public interface OnYearMonthDayPickListener extends OnDatePickListener {

        void onDatePicked(String year, String month, String day);

    }

    public interface OnYearMonthPickListener extends OnDatePickListener {

        void onDatePicked(String year, String month);

    }

    public interface OnMonthDayPickListener extends OnDatePickListener {

        void onDatePicked(String month, String day);

    }

}
