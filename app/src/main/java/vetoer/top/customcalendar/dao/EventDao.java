package vetoer.top.customcalendar.dao;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.List;

import vetoer.top.customcalendar.db.Event;

/**
 * Created by tg on 18-2-1.
 */

public class EventDao {
    private static final String TAG = "EventDao";

    public static void addEvent(Event e){
        if(e!=null){    // e不为空,继续判断
            Event findOne = queryOne(e.getMonth(),e.getDay());
            if(findOne==null){
                // 该事件不存在
                e.save();
            }else{
                // 事件存在,修改当天颜色
                findOne.setColor(e.getColor());
                findOne.save();
            }
        }else{
            Log.d(TAG, "addEvent: failed,event is null! ");
        }
    }

    public static Event queryOne(String month,int day){
        Event event = null;
        if(month != null && day>0){
            event = DataSupport
                    .where("month = ? and day = ?",month,String.valueOf(day))
                    .findFirst(Event.class);
        }
        return event;
    }

    public static List<Event> queryMonth(String month){
        List<Event> list = null;
        if(month!=null){
            list = DataSupport
                    .where("month = ?",month)
                    .find(Event.class);
        }
        return list;
    }
}
