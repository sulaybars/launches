package slybars.launches.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import slybars.launches.R;
import slybars.launches.common.dialogFragments.LaunchFilterDialogFragment;
import slybars.launches.common.dialogFragments.LaunchFilterListener;
import slybars.launches.common.helper.FilterHelper;
import slybars.launches.common.helper.SortHelper;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.model.entities.filter.LaunchFilterItem;
import slybars.launches.model.remote.DataServiceProvider;
import slybars.launches.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements LaunchFilterListener {

    @BindView(R.id.filter_and_sort_LinearLayout)
    LinearLayout filterAndSortLinearLayout;

    @BindView(R.id.launch_ListView)
    ListView launchListView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Dialog sortDialog;
    private RadioGroup sortRadioGroup;

    private LaunchFilterItem launchFilterItem;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LaunchListAdapter launchListAdapter;

    public static Intent getMainIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSpaceXLaunches();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }


    // SERVICE CALL
    private void getSpaceXLaunches() {
        progressBar.setVisibility(View.VISIBLE);
        compositeDisposable.add(DataServiceProvider.getInstance().getSpaceXLaunchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<SpaceXLaunchItem>>() {
                    @Override
                    public void onSuccess(ArrayList<SpaceXLaunchItem> result) {
                        progressBar.setVisibility(View.GONE);
                        if(result != null && result.size() > 0) {
                            hideNoDataTryAgainLinearLayout();
                            launchFilterItem = FilterHelper.getInstance().createLaunchFilterData(result);
                            sortLaunchList();
                            launchListAdapter = new LaunchListAdapter(launchFilterItem.filteredLaunchItems);
                            launchListView.setAdapter(launchListAdapter);
                            filterAndSortLinearLayout.setVisibility(View.VISIBLE);
                        } else {
                            showNoDataTryAgainLinearLayout("");
                            filterAndSortLinearLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        filterAndSortLinearLayout.setVisibility(View.GONE);
                        showNoDataTryAgainLinearLayout(e.getMessage());
                    }
                }));
    }

    // LISTENERS
    @OnClick(R.id.try_again_Button)
    public void tryAgainButtonClicked(View v) {
        getSpaceXLaunches();
    }

    @OnClick(R.id.sort_Button)
    public void sortButtonClicked(View v) {
        showSortDialog();
    }

    @OnClick(R.id.filter_Button)
    public void filterButtonClicked(View v) {
        showFilterDialog();
    }

    @Override
    public void OnFilterApplyAndDismiss(LaunchFilterItem newLaunchFilterItem) {
        launchFilterItem = newLaunchFilterItem;
        sortLaunchList();
        if(!isFinishing() && launchListAdapter != null) {
            launchListAdapter.updateData(launchFilterItem.filteredLaunchItems);
        }
    }



    // private Methods
    private void showSortDialog() {
        if (!isFinishing()) {
            if (sortDialog == null) {
                sortDialog = new Dialog(MainActivity.this);
                if (sortDialog.getWindow() != null) {
                    sortDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    sortDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    sortDialog.setContentView(R.layout.custom_dialog_sort);
                    sortDialog.setCanceledOnTouchOutside(true);

                    sortRadioGroup = (RadioGroup) sortDialog.findViewById(R.id.short_RadioGroup);
                    sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if (!isFinishing()) {
                                sortLaunchList();
                                if (launchListAdapter != null) {
                                    launchListAdapter.updateData(launchFilterItem.filteredLaunchItems);
                                    launchListView.setSelection(0);
                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        sortDialog.dismiss();
                                    }
                                }, 300);
                            }
                        }
                    });
                }
            }

            if (!isFinishing()) {
                sortDialog.show();
            }
        }
    }

    private void sortLaunchList() {
        int checkedId = R.id.sort_time_max_to_min_RadioButton;
        if (sortRadioGroup != null) {
            checkedId = sortRadioGroup.getCheckedRadioButtonId();
        }

        if (checkedId == R.id.sort_time_max_to_min_RadioButton) {
            SortHelper.getInstance().sortByTime_Max_to_Min(launchFilterItem.filteredLaunchItems);
        } else if (checkedId == R.id.sort_time_min_to_max_RadioButton) {
            SortHelper.getInstance().sortByTime_Min_to_Max(launchFilterItem.filteredLaunchItems);
        }
    }

    private void showFilterDialog() {
        if (!isFinishing()) {
            LaunchFilterDialogFragment.getInstance(MainActivity.this, launchFilterItem)
                    .show(getSupportFragmentManager(), LaunchFilterDialogFragment.TAG);
        }
    }


}
