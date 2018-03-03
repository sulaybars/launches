package slybars.launches.model.entities;

import android.text.TextUtils;

/**
 * Created by slybars on 03/03/2018.
 */

public class RocketItem {

    private String rocket_id;
    private String rocket_name;
    private String rocket_type;

    public String getRocket_name() {
        return rocket_name == null ? "" : rocket_name;
    }

    public String getRocket_type() {
        return rocket_type == null ? "" : rocket_type;
    }
}
