package bscenter.tracnghiemlaptrinh;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class ApplicationApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
