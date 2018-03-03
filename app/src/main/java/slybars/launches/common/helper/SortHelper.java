package slybars.launches.common.helper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import slybars.launches.model.entities.SpaceXLaunchItem;

/**
 * Created by slybars on 04/03/2018.
 */

public class SortHelper {

    private static SortHelper sortHelper;

    private SortHelper() {

    }

    public static SortHelper getInstance() {
        if (sortHelper == null) {
            sortHelper = new SortHelper();
        }
        return sortHelper;
    }


    // TIME
    public void sortByTime_Min_to_Max(List<SpaceXLaunchItem> list) {
        Collections.sort(list, new Comparator<SpaceXLaunchItem>() {
            @Override
            public int compare(SpaceXLaunchItem lhs, SpaceXLaunchItem rhs) {
                if (lhs.launch_date_unix > rhs.launch_date_unix) {
                    return 1;
                } else if (lhs.launch_date_unix < rhs.launch_date_unix) {
                    return -1;
                } else {
                    if (lhs.flight_number > rhs.flight_number) {
                        return 1;
                    } else if (lhs.flight_number < rhs.flight_number) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }

    public void sortByTime_Max_to_Min(List<SpaceXLaunchItem> list) {
        Collections.sort(list, new Comparator<SpaceXLaunchItem>() {
            @Override
            public int compare(SpaceXLaunchItem lhs, SpaceXLaunchItem rhs) {
                if (lhs.launch_date_unix < rhs.launch_date_unix) {
                    return 1;
                } else if (lhs.launch_date_unix > rhs.launch_date_unix) {
                    return -1;
                } else {
                    if (lhs.flight_number > rhs.flight_number) {
                        return 1;
                    } else if (lhs.flight_number < rhs.flight_number) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }

}
