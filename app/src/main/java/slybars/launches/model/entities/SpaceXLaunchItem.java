package slybars.launches.model.entities;

/**
 * Created by slybars on 03/03/2018.
 */

public class SpaceXLaunchItem {

    public int flight_number;
    public String launch_year;
    public double launch_date_unix;
    private String launch_date_utc;
    private LaunchSiteItem launch_site;
    public boolean launch_success;
    public LinksItem links;
    private String details;

    public String getDetails() {
        return details == null ? "" : details;
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
