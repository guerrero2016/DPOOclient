package Utils;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe encarregada de llegir la configuració.
 */
public class Configuration {
    private static Properties config;
    private final static String path = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "config.json";

    /**
     * Procediment encarregat de llegir el fitxer de configuració i el carregui al programa.
     */
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

    /**
     * Funció que recupera l'adreça IP de l'arxiu de configuració.
     * @return adreça IP
     */
    public static String getIPAddress () {
        return config.getProperty("IPaddress");
    }

    /**
     * Funció que recupera el port de l'arxiu de configuració
     * @return port
     */
    public static int getCommunicationPort() {
        return Integer.parseInt(config.getProperty("communicationPORT"));
    }
}
