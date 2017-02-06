package boilerhungry.backend;

import java.io.File;
import java.io.IOException;

/**
 * Created by eric on 2/6/17.
 */
public class SettingsExample {

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.load(new File("settings.json"));
    }

}
