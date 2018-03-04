package slybars.launches.model.entities;

import android.text.TextUtils;

import java.io.Serializable;

import slybars.launches.LaunchesApplication;
import slybars.launches.R;

/**
 * Created by slybars on 03/03/2018.
 */

public class SpaceXLaunchItem implements Serializable{

    public int flight_number;
    public int launch_year;
    public double launch_date_unix;
    private String launch_date_utc;
    public RocketItem rocket;
    public LaunchSiteItem launch_site;
    public boolean launch_success;
    public LinksItem links;
    private String details;

    public String getDetails() {
        if(!TextUtils.isEmpty(details)) {
            return details;
        }

        return LaunchesApplication.getApplication().getString(R.string.no_launch_detail_data);
    }

    public String getLaunch_date_utc() {
        return launch_date_utc == null ? "" : launch_date_utc;
    }

    public String getLaunchImageUrl() {
        if(links != null) {
            return  links.getMission_patch();
        }

        return "";
    }
}
