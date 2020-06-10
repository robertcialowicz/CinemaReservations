package DataBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class StartupProperties {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("user", "test");
        props.put("password", "test");
        props.put("create", "true");
        props.put("nazwa", "DataBase");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("src/main/resources/startProp.properties");
            props.store(fos, "Startup properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
