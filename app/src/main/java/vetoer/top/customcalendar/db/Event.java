package vetoer.top.customcalendar.db;

import org.litepal.crud.DataSupport;

/**
 * Created by tg on 18-2-1.
 */

public class Event extends DataSupport {
    private String month;

    private int day;
    // 0 green 1 yellow 2 red
    private int color;

    public Event() {
    }

    public Event(String month, int day, int color) {

        this.month = month;
        this.day = day;
        this.color = color;
    }

    public String getMonth() {

        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "event{" +
                "month='" + month + '\'' +
                ", day=" + day +
                ", color=" + color +
                '}';
    }
}
