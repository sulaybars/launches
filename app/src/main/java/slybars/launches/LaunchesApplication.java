package slybars.launches;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.analytics.FirebaseAnalytics;

import slybars.launches.common.helper.BackgroundManager;

/**
 * Created by slybars on 03/03/2018.
 */

public class LaunchesApplication extends Application {

    public static final String BACKGROUND_ACTION = "BACKGROUND_ACTION";
    private static LaunchesApplication launchesApplication;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        launchesApplication = this;

        Fresco.initialize(this);

        BackgroundManager.get().registerListener(new BackgroundManager.Listener() {
            @Override
            public void onBecameForeground(Activity activity) {
            }

            @Override
            public void onBecameBackground() {
                LocalBroadcastManager.getInstance(LaunchesApplication.this).sendBroadcast(new Intent(BACKGROUND_ACTION));
            }
        });
    }

    public static LaunchesApplication getApplication() {
        return launchesApplication;
    }

    public FirebaseAnalytics getFirebaseAnalytics() {
        if (firebaseAnalytics == null) {
            firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        return firebaseAnalytics;
    }

}
