package boilerhungry.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eric on 2/6/17.
 */
public class Settings {

    private int version;
    private DietaryPreferences dietaryPreferences;
    private Set<String> myFoods;

    private transient File file;

    private Settings(File file, int version, DietaryPreferences dietaryPreferences, Set<String> myFoods) {
        this.file = file;
        this.version = version;
        this.dietaryPreferences = dietaryPreferences;
        this.myFoods = myFoods;
    }

    private Settings(File file) {
        this.file = file;
        this.version = 1;
        this.dietaryPreferences = new DietaryPreferences();
        this.myFoods = new HashSet<>();
    }

    private void setFile(File file) {
        this.file = file;
    }

    public DietaryPreferences getDietaryPreferences() {
        return dietaryPreferences;
    }

    public Collection<String> getMyFoods() {
        return myFoods;
    }

    public static Settings load(File file) throws IOException {
        Gson gson = new Gson();
        Settings settings;
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                settings = gson.fromJson(reader, Settings.class);
                settings.setFile(file);
            }
        } else {
            settings = new Settings(file);
            settings.save();
        }
        return settings;
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
