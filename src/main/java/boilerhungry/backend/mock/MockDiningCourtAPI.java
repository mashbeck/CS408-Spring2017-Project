package boilerhungry.backend.mock;

import boilerhungry.backend.DiningCourtAPI;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by eric on 2/7/17.
 */
public class MockDiningCourtAPI implements DiningCourtAPI {

    @Override
    public JSONObject getJSON(URL url) throws IOException {
        return new JSONObject();
    }
}
