package boilerhungry.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2/6/17.
 */
public class Settings {

    private int version;
    private DietaryPreferences dietaryPreferences;
    private List<String> myFoods;

    private transient File file;

    private Settings(File file, int version, DietaryPreferences dietaryPreferences, List<String> myFoods) {
        this.file = file;
        this.version = version;
        this.dietaryPreferences = dietaryPreferences;
        this.myFoods = myFoods;
    }

    private Settings(File file) {
        this.file = file;
        this.version = 1;
        this.dietaryPreferences = new DietaryPreferences();
        this.myFoods = new ArrayList<>();
    }

    public static Settings load(File file) throws IOException {
        Gson gson = new Gson();
        if (file.exists()) {
            Reader reader = new FileReader(file);
            return gson.fromJson(reader, Settings.class);
        } else {
            Settings settings = new Settings(file);
            settings.save();
            return settings;
        }
    }

    public void save() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        try (Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        }
    }

}
