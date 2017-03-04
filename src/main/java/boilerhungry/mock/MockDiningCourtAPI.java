package boilerhungry.mock;

import boilerhungry.DiningCourtAPI;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockDiningCourtAPI implements DiningCourtAPI {

    private static final String DEST = "src/test/data/";
    private static Pattern locationPattern = Pattern.compile("(https://|http://)?api.hfs.purdue.edu/menus/v2/locations/?");
    private static Pattern menuPattern = Pattern.compile("(https://|http://)?api.hfs.purdue.edu/menus/v2/locations/([a-zA-Z]+)/([0-9]{2}-[0-9]{2}-[0-9]{4})/?");

    @Override
    public JSONObject getJSON(URL url) throws IOException {
        String str = url.toString();
        Matcher matcher = locationPattern.matcher(str);

        if (matcher.matches()) {
            File file = new File(DEST + "locations.json");
            String json = new String(Files.readAllBytes(file.toPath()));
            return new JSONObject(json);
        }

        matcher = menuPattern.matcher(str);
        if (matcher.matches()) {
            String diningCourt = matcher.group(2);
            String date = matcher.group(3);
            String fileName = DEST + diningCourt + "-" + date + ".json";
            File file = new File(fileName);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                return new JSONObject(json);
            } else {
                throw new FileNotFoundException("File not found: " + fileName);
            }

        }

        throw new RuntimeException("Unknown URL format: " + str);
    }

}
