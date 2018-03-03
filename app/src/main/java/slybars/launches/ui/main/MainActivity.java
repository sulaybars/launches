package slybars.launches.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import slybars.launches.R;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.model.remote.DataServiceProvider;
import slybars.launches.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.launch_ListView)
    ListView launchListView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
                            launchListView.setAdapter(new LaunchListAdapter(result));
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }
                }));
    }
}
