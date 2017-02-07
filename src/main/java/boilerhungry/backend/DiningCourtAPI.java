package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2/6/17.
 */
public class DiningCourtAPI {

    public static JSONObject getJSON(URL url) throws IOException {
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

    private static String getAddressString(JSONObject location) {
        StringBuilder res = new StringBuilder();
        JSONObject address = location.getJSONObject("Address");
        res.append(address.getString("Street")).append(" ");
        res.append(address.getString("City")).append(" ");
        res.append(address.getString("State")).append(" ");
        res.append(address.getString("ZipCode"));
        return res.toString();
    }

    public static List<DiningCourt> getDiningCourts() {
        List<DiningCourt> diningCourts = new ArrayList<>();
        try {
            JSONObject root = getJSON(new URL("https://api.hfs.purdue.edu/menus/v2/locations/"));
            JSONArray locations = root.getJSONArray("Location");
            for (int i = 0; i < locations.length(); i++) {
                JSONObject location = locations.getJSONObject(i);
                String name = location.getString("Name");
                String address = getAddressString(location);
                // TODO get hours
                // TODO the menu needs to be fetched
                diningCourts.add(new DiningCourt(name, address));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diningCourts;
    }


}
