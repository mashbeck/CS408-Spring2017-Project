package boilerhungry.backend.purdue;

import boilerhungry.backend.DiningCourtAPI;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eric on 2/6/17.
 */
public class PurdueDiningCourtAPI implements DiningCourtAPI {

    @Override
    public JSONObject getJSON(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Bad Response: " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        conn.disconnect();
        br.close();
        return new JSONObject(sb.toString());
    }

}
