package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDriven {

    public static Properties props;

    public static String getProperty(String key){
        props = new Properties();
        String rutaFile = "https://github.com/aaronbarra/DevOpsTest/blob/0b750faccce04792f0ba9ad5467397913c2edb14/src/test/resources/Properties.properties";
        try {
            InputStream input = new FileInputStream(rutaFile);
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return props.getProperty(key);
    }
}