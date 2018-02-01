package vetoer.top.customcalendar;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vetoer.top.customcalendar.dao.EventDao;
import vetoer.top.customcalendar.db.Event;
import vetoer.top.customcalendar.view.ColorPickerFragment;

public class MainActivity extends AppCompatActivity implements ColorPickerFragment.ColorDialogInterface{
    private static final String TAG = "MainActivity";
    private static final String DIALOG_COLOR = "DialogColor";

    private CustomCalendar cal;

    private Event currentEvent = null;

    private final List<DayFinish> taskList = new ArrayList<>();

    private final List<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        cal = (CustomCalendar)findViewById(R.id.cal);
        updateCurrentMonth();

        cal.setOnClickListener(new CustomCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {
                Toast.makeText(MainActivity.this,"点击减箭头",Toast.LENGTH_SHORT).show();
                cal.monthChange(-1);
                new Thread(){
                    public void run(){
                        try{
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateCurrentMonth();
                                }
                            });
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            @Override
            public void onRightRowClick() {
                Toast.makeText(MainActivity.this, "点击加箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(1);
                new Thread(){
                    public void run(){
                        try{

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateCurrentMonth();
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            @Override
            public void onTitleClick(String monthStr, Date month) {
                Toast.makeText(MainActivity.this, "点击了标题", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {
                Toast.makeText(MainActivity.this, "点击了星期", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayClick(int day, String dayStr, DayFinish finish) {
                Toast.makeText(MainActivity.this, "点击了日期", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "点击了日期:"+dayStr );
            }

            @Override
            public void onLongPress(int day, String month) {
//                Toast.makeText(MainActivity.this, "长按了日期", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "长按了日期:"+month );
                currentEvent = new Event(month,day,-1); // 默认无色

                FragmentManager manager = getSupportFragmentManager();
                ColorPickerFragment dialog = new ColorPickerFragment();
                dialog.show(manager,DIALOG_COLOR);
            }
        });
    }

    public void updateEventList(String month){
        eventList.clear();
        List<Event> queryList = EventDao.queryMonth(month);
        if(queryList!=null){
            eventList.addAll(queryList);
        }
    }

    public void updateCurrentMonth(){
        updateEventList(cal.getMonth());
        cal.setEvent(eventList);
    }

    @Override
    public void onColorClick(DialogFragment dialog, int color) {
        Log.w(TAG, "onColorClick: "+color+" month:"+currentEvent.getMonth()+" ,day: "+currentEvent.getDay() );
        if(color>2){
            color = -1;
        }
        currentEvent.setColor(color);
        EventDao.addEvent(currentEvent);

        updateCurrentMonth();
    }

    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {

    }

    class DayFinish{
        int day;
        int finish;
        int all;
        public DayFinish(int day,int finish,int all){
            this.day = day;
            this.finish = finish;
            this.all = all;
        }
    }
}
