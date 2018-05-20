package Utils;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

    /**
     * Classe singleton encarregada de llegir la configuració.
     */
    public class Configuration {
    private static Configuration ourInstance = new Configuration();
    private Properties config;
    private final static String path = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "config.json";

    /**
     * Constructor sense paràmetres
     * Procediment encarregat de llegir el fitxer de configuració i el carregui al programa.
     */
    private Configuration() {}

    /**
     * Getter del singleton.
     * @return Singleton de configuració.
     */
    public static Configuration getInstance() {return ourInstance;}

    /**
     * Carrega els paràmetres de configuració a partir del config.json
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
     * Getter de l'adreça IP del client.
     * @return Adreça IP.
     */
    public String getIPAddress () {
        return config.getProperty("IPaddress");
    }

    /**
     * Getter del port de comunicació amb el servidor.
     * @return Port de comunicació amb el servidor.
     */
    public int getCommunicationPort() {
        return Integer.parseInt(config.getProperty("communicationPORT"));
    }
}
