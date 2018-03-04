package slybars.launches.common.helper;

import com.google.firebase.analytics.FirebaseAnalytics;

import slybars.launches.LaunchesApplication;
import slybars.launches.ui.base.BaseActivity;

/**
 * Created by slybars on 04/03/2018.
 */

public class LogHelper {

    /**
     * FireBase Analytics Screen Names
     */
    public static final String logScreenName_main = "FirlatmaListesi";
    public static final String logScreenName_launchDetail = "FirlatmaDetayi";

    private static LogHelper logHelper;

    private LogHelper() {

    }

    public static LogHelper getInstance() {
        if (logHelper == null) {
            logHelper = new LogHelper();
        }
        return logHelper;
    }

    public void logScreenName(BaseActivity activity, String screenName) {
        if (activity != null && !activity.isFinishing()) {
            FirebaseAnalytics analytics = LaunchesApplication.getApplication().getFirebaseAnalytics();
            analytics.setCurrentScreen(activity, screenName, activity.getClass().getName());
        }
    }
}
