package slybars.launches.common.dialogFragments;

import slybars.launches.model.entities.filter.LaunchFilterItem;

/**
 * Created by slybars on 04/03/2018.
 */

public interface LaunchFilterListener {
    void OnFilterApplyAndDismiss(LaunchFilterItem launchFilterItem);
}
