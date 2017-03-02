package boilerhungry.backend.demo;

import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadJSON {

    private static final String DEST = "src/main/test/data/";
    private static final String BASE = "https://api.hfs.purdue.edu/menus/v2/locations/";
    private final DiningCourtAPI api = new PurdueDiningCourtAPI();

    public static void main(String[] args) throws IOException {
        DownloadJSON dj = new DownloadJSON();
        dj.downloadLocations();
        dj.downloadDiningCourtMenu("Wiley", "02-07-2017");
    }

    public void downloadLocations() throws IOException {
        JSONObject jsonObject = api.getJSON(new URL(BASE));
        String prettyJsonString = jsonObject.toString(4);
        String file = DEST + "locations.json";
        Files.write(Paths.get(file), prettyJsonString.getBytes());
        System.out.println("Saved " + file);
    }

    public void downloadDiningCourtMenu(String diningCourt, String date) throws IOException {
        String link = BASE + diningCourt + "/" + date;
        JSONObject jsonObject = api.getJSON(new URL(link));
        String prettyJsonString = jsonObject.toString(4);
        String file = DEST + diningCourt + "-" + date + ".json";
        Files.write(Paths.get(file), prettyJsonString.getBytes());
        System.out.println("Saved " + file);
    }

}
