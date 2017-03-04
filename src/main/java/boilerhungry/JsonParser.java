package boilerhungry;

import org.json.JSONObject;

import java.util.Optional;

public abstract class JsonParser {

    protected <T> Optional<T> get(JSONObject json, String key, Class<T> clazz) {
        if (!exists(json, key)) {
            return Optional.empty();
        } else {
            return Optional.of(clazz.cast(json.get(key)));
        }
    }

    protected boolean exists(JSONObject json, String key) {
        return !json.isNull(key) && json.has(key);
    }

}
