import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Matthew on 2/6/2017.
 */
public class SavePreferences {
    public static void main(String[] args) {

    }

    public JSONObject readSaveFile(){
        try {
            FileReader fileReader = new FileReader("Settings.json");
            char buffer[] = new char[2048];
            fileReader.read(buffer);
            fileReader.close();
            String jsonstring = String.copyValueOf(buffer);
            JSONObject ret = new JSONObject(jsonstring);
            System.out.println(jsonstring);
            return ret;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    public void createSaveFile(){
        JSONObject save = new JSONObject();
        JSONArray preferences = new JSONArray();
        JSONArray myfoods = new JSONArray();
        JSONObject object;
        object = new JSONObject();
        object.put("Vegetarian Only", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Eggs", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Fish", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Gluten", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Milk", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Peanuts", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Shellfish", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Soy", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Tree Nuts", false);
        preferences.put(object);
        object = new JSONObject();
        object.put("No Wheat", false);
        preferences.put(object);
        save.put("Preferences", preferences);
        save.put("MyFoods",myfoods);
        String json = save.toString();
        try {
            FileWriter fileWriter = new FileWriter("Settings.json");
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
