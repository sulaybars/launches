package slybars.launches.model.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by slybars on 03/03/2018.
 */

public class OkHttpProvider {

    private static OkHttpClient sOkHttpClient;
    private static int timeOut = 60;

    public OkHttpProvider() {
    }

    public static OkHttpClient getInstance() {
        if (sOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(timeOut, TimeUnit.SECONDS)
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS);
            sOkHttpClient = builder.build();
        }
        return sOkHttpClient;
    }
}
