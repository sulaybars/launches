package slybars.launches.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import slybars.launches.R;
import slybars.launches.model.entities.SpaceXLaunchItem;
import slybars.launches.model.remote.DataServiceProvider;
import slybars.launches.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static Intent getMainIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSpaceXLaunches();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }


    // SERVICE CALL
    private void getSpaceXLaunches() {
        compositeDisposable.add(DataServiceProvider.getInstance().getSpaceXLaunchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<SpaceXLaunchItem>>() {
                    @Override
                    public void onSuccess(ArrayList<SpaceXLaunchItem> result) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }));
    }
}
