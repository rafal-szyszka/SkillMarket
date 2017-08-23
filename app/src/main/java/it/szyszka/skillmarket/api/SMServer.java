package it.szyszka.skillmarket.api;

import java.util.Properties;

import it.szyszka.skillmarket.utils.PropertiesReader;

/**
 * Created by rafal on 19.08.17.
 */

public class SMServer {

    public static final String API_CONFIG_ASSET_NAME = "api.config";

    private Properties config;

    public static SMServer init(PropertiesReader reader) {
        SMServer server = new SMServer();
        server.applyConfiguration(reader.readProperties(API_CONFIG_ASSET_NAME));
        return server;
    }

    public void applyConfiguration(Properties configuration) {
        config = configuration;
    }

    public Properties restoreConfiguration() {
        return config;
    }

    public class KEYS {
        public static final String API_HOST = "host";
        public static final String TEST_ENDPOINT = "api_test";
    }

}
