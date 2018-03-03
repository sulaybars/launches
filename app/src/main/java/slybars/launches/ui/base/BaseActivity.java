package slybars.launches.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import slybars.launches.R;

/**
 * Created by slybars on 03/03/2018.
 */

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.no_data_LinearLayout)
    LinearLayout noDataLinearLayout;
    @Nullable
    @BindView(R.id.no_data_message_TextView)
    TextView noDataMessageTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * No Data Try Again View
     * @param errorMessage error Message
     */
    public void showNoDataTryAgainLinearLayout(String errorMessage) {
        if(noDataLinearLayout != null) {
            noDataLinearLayout.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.error_occurred);
        }
        if(noDataMessageTextView != null) {
            noDataMessageTextView.setText(errorMessage);
        }
    }

    public void hideNoDataTryAgainLinearLayout() {
        if(noDataLinearLayout != null) {
            noDataLinearLayout.setVisibility(View.GONE);
        }
    }
}
