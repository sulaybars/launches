package slybars.launches.common.dialogFragments;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slybars.launches.LaunchesApplication;
import slybars.launches.R;
import slybars.launches.common.helper.FilterHelper;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.model.entities.filter.LaunchFilterItem;

/**
 * Created by slybars on 04/03/2018.
 */

public class LaunchFilterDialogFragment extends android.support.v4.app.DialogFragment {

    public static String TAG = LaunchFilterDialogFragment.class.getSimpleName();
    private static final String LAUNCH_FILTER_ITEM_EXTRA = "LAUNCH_FILTER_ITEM_EXTRA";

    @BindView(R.id.filter_RangeSeekBar)
    CrystalRangeSeekbar filterCrystalRangeSeekBar;

    @BindView(R.id.filter_range_value_TextView)
    TextView filterRangeTextView;

    @BindView(R.id.filtered_count_TextView)
    TextView countTextView;

    private LaunchFilterListener listener;
    private LaunchFilterItem launchFilterItem;

    /**
     * This dialog fragment used for showing custom design dialog.
     *
     * @param clickListener LaunchFilterListener
     * @param launchFilterItem LaunchFilterItem
     * @return LaunchFilterDialogFragment ready for showing.
     */
    public static LaunchFilterDialogFragment getInstance(LaunchFilterListener clickListener, LaunchFilterItem launchFilterItem) {
        LaunchFilterDialogFragment fragment = new LaunchFilterDialogFragment();
        fragment.listener = clickListener;

        Bundle bundle = new Bundle();
        bundle.putSerializable(LAUNCH_FILTER_ITEM_EXTRA, launchFilterItem);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        launchFilterItem = (LaunchFilterItem) getArguments().getSerializable(LAUNCH_FILTER_ITEM_EXTRA);

        initFilterView();
        filterCrystalRangeSeekBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if(launchFilterItem != null) {
                    launchFilterItem.yearFilterRangeItem.tempSelectedMaxValue = maxValue.intValue();
                    launchFilterItem.yearFilterRangeItem.tempSelectedMinValue = minValue.intValue();
                    filterRangeTextView.setText(getString(R.string.filter_range_value_format,
                            launchFilterItem.yearFilterRangeItem.tempSelectedMinValue,
                            launchFilterItem.yearFilterRangeItem.tempSelectedMaxValue));

                    ArrayList<SpaceXLaunchItem> tempList = FilterHelper.getInstance().applyYearRangeFilterWithTempValues(launchFilterItem.launchItems,
                            launchFilterItem.yearFilterRangeItem);
                    countTextView.setText(getString(R.string.filtered_launch_count,
                            tempList.size(),
                            launchFilterItem.launchItems.size()));
                }
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        getDialog().setCanceledOnTouchOutside(true);

        View rootView = inflater.inflate(R.layout.filter_dialog_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.apply_Button)
    public void OnApplyButtonClicked(View v) {
        launchFilterItem.yearFilterRangeItem.applyTempValues();
        FilterHelper.getInstance().applyFilter(launchFilterItem);
        dismiss();
    }

    @OnClick(R.id.clear_filter_Button)
    public void OnClearFilterButtonClicked(View v) {
        launchFilterItem = FilterHelper.getInstance().clearFilter();
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(listener != null) {
            listener.OnFilterApplyAndDismiss(launchFilterItem);
        }
    }

    private void initFilterView() {

        filterCrystalRangeSeekBar.setMinStartValue(launchFilterItem.yearFilterRangeItem.selectedMinValue);
        filterCrystalRangeSeekBar.setMaxStartValue(launchFilterItem.yearFilterRangeItem.selectedMaxValue);

        filterCrystalRangeSeekBar.setMinValue(launchFilterItem.yearFilterRangeItem.minValue);
        filterCrystalRangeSeekBar.setMaxValue(launchFilterItem.yearFilterRangeItem.maxValue);

        filterCrystalRangeSeekBar.setSteps(1);
        filterCrystalRangeSeekBar.apply();
    }
}
