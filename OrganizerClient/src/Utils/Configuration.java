package Utils;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Properties config;
    //No fer push d'aixo.
    private final static String path = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "config.json";

    public static void loadConfiguration() {
        BufferedReader br = null;
        config = new Properties();

        try {
            br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            config.setProperty("IPaddress", jsonObject.getString("IPaddress"));
            config.setProperty("communicationPORT", jsonObject.getString("communicationPORT"));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getIPAddress () {
        return config.getProperty("IPaddress");
    }

    public static int getCommunicationPort() {
        return Integer.parseInt(config.getProperty("communicationPORT"));
    }
}
