package slybars.launches.model.entities;

import java.io.Serializable;

/**
 * Created by slybars on 03/03/2018.
 */

public class LaunchSiteItem implements Serializable {

    private String site_id;
    private String site_name;
    private String site_name_long;

    public String getLaunch_site() {
        return site_name == null ? "" : site_name;
    }

    public String getSite_name_long() {
        return site_name_long == null ? "" : site_name_long;
    }
}
