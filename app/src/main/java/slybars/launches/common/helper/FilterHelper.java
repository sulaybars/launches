package slybars.launches.common.helper;

import java.util.ArrayList;

import slybars.launches.model.entities.filter.FilterRangeItem;
import slybars.launches.model.entities.filter.LaunchFilterItem;
import slybars.launches.model.entities.SpaceXLaunchItem;

/**
 * Created by slybars on 04/03/2018.
 */

public class FilterHelper {

    private static FilterHelper mFilterHelper;

    private LaunchFilterItem launchFilterItem;

    protected FilterHelper() {

    }

    public static FilterHelper getInstance() {
        if (mFilterHelper == null) {
            mFilterHelper = new FilterHelper();
        }
        return mFilterHelper;
    }


    // ******** Create Filter ********
    public LaunchFilterItem createLaunchFilterData(ArrayList<SpaceXLaunchItem> launchItems) {

        launchFilterItem = new LaunchFilterItem(launchItems);

        // init min value
        if(launchItems.size() > 0 && launchItems.get(0) != null) {
            launchFilterItem.yearFilterRangeItem.setAllMinValues(launchItems.get(0).launch_year);
        }

        // set min max value
        for (SpaceXLaunchItem launchItem : launchItems) {
            setYear_MinMax(launchFilterItem.yearFilterRangeItem, launchItem);
        }
        return launchFilterItem;
    }


    private void setYear_MinMax(FilterRangeItem yearFilterItem, SpaceXLaunchItem launchItem) {
        if (yearFilterItem.maxValue < launchItem.launch_year) {
            yearFilterItem.setAllMaxValues(launchItem.launch_year);
        }

        if (yearFilterItem.minValue > launchItem.launch_year) {
            yearFilterItem.setAllMinValues(launchItem.launch_year);
        }
    }


    // ******** Apply Filter ********
    public void applyFilter(LaunchFilterItem launchFilterItem) {
        ArrayList<SpaceXLaunchItem> filteredLaunchItems = new ArrayList<>(launchFilterItem.launchItems);

        launchFilterItem.filteredLaunchItems = applyYearRangeFilter(filteredLaunchItems);
    }

    private ArrayList<SpaceXLaunchItem> applyYearRangeFilter(ArrayList<SpaceXLaunchItem> launchItems) {
        ArrayList<SpaceXLaunchItem> filteredLaunchList = new ArrayList<>();

        for (SpaceXLaunchItem launchItem : launchItems) {
            if (launchItem.launch_year <= launchFilterItem.yearFilterRangeItem.selectedMaxValue
                    && launchItem.launch_year >= launchFilterItem.yearFilterRangeItem.selectedMinValue) {
                filteredLaunchList.add(launchItem);
            }
        }

        return filteredLaunchList;
    }



    // ******** Clear Filter ********
    public LaunchFilterItem clearFilter() {
        if(launchFilterItem != null) {
            launchFilterItem = createLaunchFilterData(launchFilterItem.launchItems);
        }
        return launchFilterItem;
    }


    // ******** Update Filter ********
    public LaunchFilterItem updateFilter(LaunchFilterItem oldLaunchFilterItem, ArrayList<SpaceXLaunchItem> newLaunchItems) {

        // 1- Create new filter
        LaunchFilterItem newLaunchFilterItem = createLaunchFilterData(newLaunchItems);

        // 2- Update new filter
        updateYearRangeFilter(oldLaunchFilterItem.yearFilterRangeItem);

        // 3- Apply new filter
        applyFilter(newLaunchFilterItem);

        return newLaunchFilterItem;
    }

    private void updateYearRangeFilter(FilterRangeItem oldRangeItem) {
        launchFilterItem.yearFilterRangeItem.selectedMinValue = oldRangeItem.selectedMinValue < launchFilterItem.yearFilterRangeItem.minValue
                ? launchFilterItem.yearFilterRangeItem.minValue : oldRangeItem.selectedMinValue;
        launchFilterItem.yearFilterRangeItem.selectedMaxValue = oldRangeItem.selectedMaxValue > launchFilterItem.yearFilterRangeItem.maxValue
                ? launchFilterItem.yearFilterRangeItem.maxValue : oldRangeItem.selectedMaxValue;
    }


}
