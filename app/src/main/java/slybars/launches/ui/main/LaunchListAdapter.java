package slybars.launches.ui.main;

import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import slybars.launches.LaunchesApplication;
import slybars.launches.R;
import slybars.launches.common.helper.DateHelper;
import slybars.launches.model.entities.SpaceXLaunchItem;

/**
 * Created by slybars on 03/03/2018.
 */

public class LaunchListAdapter extends BaseAdapter {

    private ArrayList<SpaceXLaunchItem> dataSource;

    public LaunchListAdapter(ArrayList<SpaceXLaunchItem> data) {
        this.dataSource = data;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public SpaceXLaunchItem getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LaunchViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_launch, parent, false);
            holder = new LaunchViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (LaunchViewHolder) convertView.getTag();
        }

        SpaceXLaunchItem launchItem = getItem(position);
        String launchDate = DateHelper.getInstance().convertServiceDateToShortDate(launchItem.getLaunch_date_utc());
        holder.launchInfoTextView.setText(parent.getContext().getString(R.string.launch_info_format, launchDate, launchItem.rocket.getRocket_name()));

        holder.launchStateTextView.setText(parent.getContext().getString(launchItem.launch_success ? R.string.launch_success : R.string.launch_failed));
        holder.launchStateTextView.setTextColor(ContextCompat.getColor(parent.getContext(), launchItem.launch_success ? R.color.success_state_color : R.color.failed_state_color));

        String imageUrl = launchItem.getLaunchImageUrl();
        int size = (int) (44.0f * LaunchesApplication.getApplication().getResources().getDisplayMetrics().density);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setResizeOptions(new ResizeOptions(size, size))
                .build();

        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        controller.setImageRequest(request);
        holder.launchImageView.setController(controller.build());

        return convertView;
    }

    static class LaunchViewHolder {

        @BindView(R.id.launch_ImageView)
        SimpleDraweeView launchImageView;

        @BindView(R.id.launch_info_TextView)
        TextView launchInfoTextView;

        @BindView(R.id.launch_state_TextView)
        TextView launchStateTextView;

        private LaunchViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}