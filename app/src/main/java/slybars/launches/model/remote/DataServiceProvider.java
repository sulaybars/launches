package slybars.launches.model.remote;

/**
 * Created by slybars on 03/03/2018.
 */

public class DataServiceProvider {

    private static ServiceInterface spaceXServiceProvider;
    
    public static ServiceInterface getInstance() {
        if (spaceXServiceProvider == null) {
            spaceXServiceProvider = SpaceXServiceProvider.getInstance().create(ServiceInterface.class);
        }
        return spaceXServiceProvider;
    }
    
}
