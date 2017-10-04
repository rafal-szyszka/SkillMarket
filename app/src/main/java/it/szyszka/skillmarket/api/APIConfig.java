package it.szyszka.skillmarket.api;

import java.util.Properties;

import it.szyszka.skillmarket.modules.user.api.UserService;
import it.szyszka.skillmarket.utils.PropertiesReader;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rafal on 19.08.17.
 */

public class APIConfig {

    public static final String TAG = APIConfig.class.getSimpleName();

    private static final String API_CONFIG_ASSET_NAME = "api.config";
    private static APIConfig api;

    private Retrofit retrofit;
    private Properties config;

    public static APIConfig init(PropertiesReader reader) {
        api = new APIConfig();
        api.applyConfiguration(reader.readProperties(API_CONFIG_ASSET_NAME));
        api.retrofit = new Retrofit.Builder()
                .baseUrl(api.config.getProperty(Keys.API_HOST))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return api;
    }

    public static APIConfig getInstance() {
        return api;
    }

    public UserService createUserApiClient() {
        return retrofit.create(UserService.class);
    }

    private void applyConfiguration(Properties configuration) {
        config = configuration;
    }

    public Properties restoreConfiguration() {
        return config;
    }

    public static class Keys {
        public static String API_HOST = "host";
    }

}
