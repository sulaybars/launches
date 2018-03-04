package slybars.launches.ui.launchdetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slybars.launches.LaunchesApplication;
import slybars.launches.R;
import slybars.launches.common.helper.DateHelper;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.ui.base.BaseActivity;

/**
 * Created by slybars on 04/03/2018.
 */

public class LaunchDetailActivity extends BaseActivity {

    private static final String SPACE_X_LAUNCH_ITEM_EXTRA = "SPACE_X_LAUNCH_ITEM_EXTRA";

    //TOOLBAR
    @BindView(R.id.toolbar_launch_detail)
    Toolbar toolbar;

    @BindView(R.id.launch_detail_ImageView)
    SimpleDraweeView launchImageView;

    @BindView(R.id.launch_detail_date_TextView)
    TextView launchDateTextView;

    @BindView(R.id.launch_detail_state_TextView)
    TextView launchStateTextView;

    @BindView(R.id.launch_site_TextView)
    TextView launchSiteTextView;

    @BindView(R.id.rocket_info_TextView)
    TextView rocketInfoTextView;

    @BindView(R.id.launch_detail_TextView)
    TextView launchDetailTextView;

    private SpaceXLaunchItem spaceXLaunchItem;

    public static Intent getLaunchDetailIntent(Context context, SpaceXLaunchItem spaceXLaunchItem) {
        Intent launchDetailIntent = new Intent(context, LaunchDetailActivity.class);
        launchDetailIntent.putExtra(SPACE_X_LAUNCH_ITEM_EXTRA, spaceXLaunchItem);
        return launchDetailIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);

        ButterKnife.bind(this);

        spaceXLaunchItem = (SpaceXLaunchItem) getIntent().getExtras().getSerializable(SPACE_X_LAUNCH_ITEM_EXTRA);

        initActionBar();
        initView();
    }


    private void initView() {
        launchDateTextView.setText(DateHelper.getInstance().convertServiceDateToShortDate(spaceXLaunchItem.getLaunch_date_utc()));

        launchStateTextView.setText(getString(spaceXLaunchItem.launch_success ? R.string.launch_success : R.string.launch_failed));
        launchStateTextView.setTextColor(ContextCompat.getColor(this, spaceXLaunchItem.launch_success ? 
                R.color.success_state_color : R.color.failed_state_color));

        String imageUrl = spaceXLaunchItem.getLaunchImageUrl();
        int size = (int) (44.0f * LaunchesApplication.getApplication().getResources().getDisplayMetrics().density);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setResizeOptions(new ResizeOptions(size, size))
                .build();

        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        controller.setImageRequest(request);
        launchImageView.setController(controller.build());

        launchSiteTextView.setText(getString(R.string.launch_site, spaceXLaunchItem.launch_site.getSite_name_long()));
        rocketInfoTextView.setText(getString(R.string.rocket_info,
                spaceXLaunchItem.rocket.getRocket_name(),
                spaceXLaunchItem.rocket.getRocket_type()));
        launchDetailTextView.setText(spaceXLaunchItem.getDetails());
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
