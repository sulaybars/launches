package slybars.launches.ui.launchdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import slybars.launches.LaunchesApplication;
import slybars.launches.R;
import slybars.launches.common.helper.DateHelper;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.ui.base.BaseFragment;

/**
 * Created by slybars on 07/03/2018.
 */

public class LaunchDetailFragment extends BaseFragment {

    private static final String SPACE_X_LAUNCH_ITEM_EXTRA = "SPACE_X_LAUNCH_ITEM_EXTRA";

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
    private View fragmentView;

    public static LaunchDetailFragment getInstance(SpaceXLaunchItem spaceXLaunchItem) {
        LaunchDetailFragment launchDetailFragment = new LaunchDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SPACE_X_LAUNCH_ITEM_EXTRA, spaceXLaunchItem);
        launchDetailFragment.setArguments(bundle);
        return launchDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spaceXLaunchItem = (SpaceXLaunchItem) getArguments().getSerializable(SPACE_X_LAUNCH_ITEM_EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(fragmentView == null) {
            fragmentView = inflater.inflate(R.layout.fragment_launch_detail, container, false);
            ButterKnife.bind(this, fragmentView);
        }

        initView();

        return  fragmentView;
    }


    // Private Methods
    private void initView() {
        launchDateTextView.setText(DateHelper.getInstance().convertServiceDateToShortDate(spaceXLaunchItem.getLaunch_date_utc()));

        launchStateTextView.setText(getString(spaceXLaunchItem.launch_success ? R.string.launch_success : R.string.launch_failed));
        launchStateTextView.setTextColor(ContextCompat.getColor(getActivity(), spaceXLaunchItem.launch_success ?
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

}
