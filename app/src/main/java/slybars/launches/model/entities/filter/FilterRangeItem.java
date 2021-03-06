package slybars.launches.model.entities.filter;

import java.io.Serializable;

/**
 * Created by slybars on 04/03/2018.
 */

public class FilterRangeItem implements Serializable {

    public int minValue;
    public int maxValue;

    public int selectedMinValue;
    public int selectedMaxValue;

    public int tempSelectedMinValue;
    public int tempSelectedMaxValue;

    public FilterRangeItem() {
    }

    public void setAllMinValues(int min) {
        minValue = min;
        selectedMinValue = min;
        tempSelectedMinValue = min;
    }

    public void setAllMaxValues(int max) {
        maxValue = max;
        selectedMaxValue = max;
        tempSelectedMaxValue = max;
    }

    public void applyTempValues() {
        selectedMaxValue = tempSelectedMaxValue;
        selectedMinValue = tempSelectedMinValue;
    }

}
