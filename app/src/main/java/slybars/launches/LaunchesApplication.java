package slybars.launches;

import android.app.Application;

/**
 * Created by slybars on 03/03/2018.
 */

public class LaunchesApplication extends Application {

    private static LaunchesApplication launchesApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        launchesApplication = this;
    }

    public static LaunchesApplication getLaunchesApplication() {
        return launchesApplication;
    }


}
