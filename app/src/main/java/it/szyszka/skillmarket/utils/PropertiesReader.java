package it.szyszka.skillmarket.utils;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rafal on 19.08.17.
 */

public class PropertiesReader {

    public static final String TAG = PropertiesReader.class.getName();

    private AssetManager assetManager;
    private Properties properties;

    public PropertiesReader(AssetManager assetManager, Properties properties) {
        this.assetManager = assetManager;
        this.properties = properties;
    }

    public Properties readProperties(String propertiesName){
        try {
            InputStream inputStream = assetManager.open(propertiesName);
            properties.load(inputStream);
            Log.i(TAG, "Successfully loaded \"" + propertiesName + "\".");
        } catch (IOException e) {
            Log.e(TAG, "Failed to load \"" + propertiesName + "\"!\n" + e.getLocalizedMessage());
        }
        return properties;
    }
}
