package boilerhungry;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public interface DiningCourtAPI {

    JSONObject getJSON(URL url) throws IOException;

}
