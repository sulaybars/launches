package slybars.launches.model.entities;

/**
 * Created by slybars on 03/03/2018.
 */

public class LaunchSiteItem {

    private String site_id;
    private String launch_site;
    private String site_name_long;

    public String getLaunch_site() {
        return launch_site == null ? "" : launch_site;
    }

    public String getSite_name_long() {
        return site_name_long == null ? "" : site_name_long;
    }
}
