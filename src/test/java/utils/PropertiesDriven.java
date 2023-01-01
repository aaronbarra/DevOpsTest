package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDriven {

    public static Properties props;

    public static String getProperty(String key){
        props = new Properties();
        String rutaFile = "/home/aaron.araya94/Descargas/untitled/src/test/resources/Properties.properties";
        try {
            InputStream input = new FileInputStream(rutaFile);
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return props.getProperty(key);
    }
}