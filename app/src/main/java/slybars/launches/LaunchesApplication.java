package slybars.launches;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by slybars on 03/03/2018.
 */

public class LaunchesApplication extends Application {

    private static LaunchesApplication launchesApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        launchesApplication = this;

        Fresco.initialize(this);
    }

    public static LaunchesApplication getApplication() {
        return launchesApplication;
    }


}
