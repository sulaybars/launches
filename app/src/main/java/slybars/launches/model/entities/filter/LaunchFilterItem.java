package slybars.launches.model.entities.filter;

import java.io.Serializable;
import java.util.ArrayList;

import slybars.launches.model.entities.SpaceXLaunchItem;

/**
 * Created by slybars on 04/03/2018.
 */

public class LaunchFilterItem implements Serializable {

    public ArrayList<SpaceXLaunchItem> launchItems;
    public ArrayList<SpaceXLaunchItem> filteredLaunchItems;
    public FilterRangeItem yearFilterRangeItem;

    public LaunchFilterItem(ArrayList<SpaceXLaunchItem> spaceXLaunchItems) {
        launchItems = new ArrayList<>(spaceXLaunchItems);
        filteredLaunchItems = new ArrayList<>(spaceXLaunchItems);
        yearFilterRangeItem = new FilterRangeItem();
    }

}
