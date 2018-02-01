package vetoer.top.customcalendar.tools;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by tg on 18-2-1.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

    public static Context getContext(){
        return context;
    }
}
