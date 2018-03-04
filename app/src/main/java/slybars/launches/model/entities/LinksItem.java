package slybars.launches.model.entities;

import java.io.Serializable;

/**
 * Created by slybars on 03/03/2018.
 */

public class LinksItem implements Serializable {

    private String mission_patch;

    public String getMission_patch() {
        return mission_patch == null ? "" : mission_patch;
    }
}
