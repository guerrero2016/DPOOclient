package Utils;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

    /**
     * Classe singleton encarregada de llegir la configuracio
     */
    public class Configuration {
    private static Configuration ourInstance = new Configuration();
    private Properties config;
    private final static String path = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "config.json";

    /**
     * Constructor sense parametres
     * Procediment encarregat de llegir el fitxer de configuracio i la carregui al programa
     */
    private Configuration() {}

    /**
     * Getter del singleton
     * @return Singleton de configuracio
     */
    public static Configuration getInstance() {return ourInstance;}

    /**
     * Carrega els parametres de configuracio a partir del fitxer config.json
     */
    public void loadConfiguration() {
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
     * Getter de l'adreca IP del client
     * @return Adreca IP
     */
    public String getIPAddress () {
        return config.getProperty("IPaddress");
    }

    /**
     * Getter del port de comunicacio amb el servidor
     * @return Port de comunicacio amb el servidor
     */
    public int getCommunicationPort() {
        return Integer.parseInt(config.getProperty("communicationPORT"));
    }
}
