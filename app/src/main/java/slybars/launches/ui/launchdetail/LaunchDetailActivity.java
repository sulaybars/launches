package slybars.launches.ui.launchdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slybars.launches.R;
import slybars.launches.common.helper.LogHelper;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.ui.base.BaseActivity;

/**
 * Created by slybars on 04/03/2018.
 */

public class LaunchDetailActivity extends BaseActivity {

    private static String LAUNCH_LIST_ITEM_EXTRA = "LAUNCH_LIST_ITEM_EXTRA";
    private static String INDEX_EXTRA = "INDEX_EXTRA";

    //TOOLBAR
    @BindView(R.id.toolbar_launch_detail)
    Toolbar toolbar;

    @BindView(R.id.launch_detail_ViewPager)
    ViewPager launchDetailViewPager;

    private ArrayList<SpaceXLaunchItem> launchList;
    private int index;

    public static Intent getLaunchDetailIntent(Context context, ArrayList<SpaceXLaunchItem> launchItems, int index) {
        Intent launchDetailIntent = new Intent(context, LaunchDetailActivity.class);
        launchDetailIntent.putExtra(LAUNCH_LIST_ITEM_EXTRA, launchItems);
        launchDetailIntent.putExtra(INDEX_EXTRA, index);
        return launchDetailIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);

        ButterKnife.bind(this);

        launchList = (ArrayList<SpaceXLaunchItem>) getIntent().getExtras().getSerializable(LAUNCH_LIST_ITEM_EXTRA);
        index = getIntent().getIntExtra(INDEX_EXTRA, 0);

        initActionBar();

        LaunchDetailPagerAdapter adapter = new LaunchDetailPagerAdapter(getSupportFragmentManager(), launchList);
        launchDetailViewPager.setAdapter(adapter);
        launchDetailViewPager.setCurrentItem(index);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogHelper.getInstance().logScreenName(LaunchDetailActivity.this, LogHelper.logScreenName_launchDetail);
    }

    // OnClick LISTENERS
    @OnClick(R.id.back_Button)
    public void backButtonClicked(View v) {
        finish();
    }
    
    // ACTION BAR
    private void initActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

}
