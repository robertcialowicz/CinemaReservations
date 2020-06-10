package DataBase;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class StartProperties {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("user", "test");
        props.put("password", "test");
        props.put("create", "true");
        props.put("name", "DataBase");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("src/main/resources/startProperties.xml");
            props.storeToXML(fos, "Startup properties", String.valueOf(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
