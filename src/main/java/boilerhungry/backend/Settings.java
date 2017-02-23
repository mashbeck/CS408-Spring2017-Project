package boilerhungry.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Settings {

    private int version;
    private Set<String> myFoods;
    private Set<String> dietaryPreferences;
    private Set<String> dietaryExclusions;

    private transient File file;

    private Settings(File file,
                     int version,
                     Set<String> dietaryPreferences,
                     Set<String> dietaryExclusions,
                     Set<String> myFoods) {
        this.file = file;
        this.version = version;
        this.dietaryPreferences = dietaryPreferences;
        this.dietaryExclusions = dietaryExclusions;
        this.myFoods = myFoods;
    }

    private Settings(File file) {
        this.file = file;
        this.version = 1;
        this.dietaryExclusions = new HashSet<>();
        this.myFoods = new HashSet<>();
    }

    private void setFile(File file) {
        this.file = file;
    }

    public Collection<String> getDietaryExclusions() {
        return dietaryExclusions;
    }

    public Collection<String> getDietaryPreferences() {
        return dietaryPreferences;
    }

    public boolean isDietaryExclusion(String food) {
        return dietaryExclusions.contains(food);
    }

    public boolean hasDietaryPreference(String preference) {
        return dietaryPreferences.contains(preference);
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
