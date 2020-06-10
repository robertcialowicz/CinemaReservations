package DataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {
    public static void main(String[] args) {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/startProp.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Entry<Object, Object>> pr = props.entrySet();
        for (Entry<Object, Object> entry : pr) {
            String nazwa = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println(nazwa + "=" + value);
        }
        String user = props.getProperty("user");
        System.out.println(user);
    }
}
