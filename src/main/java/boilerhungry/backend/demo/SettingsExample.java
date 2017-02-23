package boilerhungry.backend.demo;

import boilerhungry.backend.Settings;

import java.io.File;
import java.io.IOException;

/**
 * Created by eric on 2/6/17.
 */
public class SettingsExample {

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.load(new File("settings.json"));
        settings.getMyFoods().add("bacon");
        settings.getMyFoods().add("strawberries");
        settings.getDietaryExclusions().add("egg");
        settings.getDietaryExclusions().add("fish");
        settings.save();
    }

}
