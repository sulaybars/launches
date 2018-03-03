package slybars.launches.model.remote;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;
import slybars.launches.model.entities.SpaceXLaunchItem;

/**
 * Created by slybars on 03/03/2018.
 */

public interface ServiceInterface {

    @GET(ServiceUrls.Launches)
    Single<ArrayList<SpaceXLaunchItem>> getSpaceXLaunchList();

}
