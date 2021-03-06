package boilerhungry;

import java.util.*;
import java.util.stream.Collectors;

public class Food {

    private String name;
    private String station;
    private Map<String, Boolean> allergens;
    private boolean isVegetarian;

    public Food(String name, String station) {
        this.name = name;
        this.station = station;
        this.allergens = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.isVegetarian = vegetarian;
    }

    public List<String> getAllergens() {
        return allergens.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
    }

    public List<String> getPossibleAllergens() {
        return allergens.keySet().stream()
                .collect(Collectors.toList());
    }

    public List<String> getExclusions(Settings settings) {
        return settings.getDietaryExclusions().stream()
                .filter(this::hasAllergen)
                .collect(Collectors.toList());
    }

    public List<String> getPreferences(Settings settings) {
        if (settings.isDietaryPreference("vegetarian") && isVegetarian) {
            return Arrays.asList("vegetarian");
        } else {
            return new ArrayList<>();
        }
    }

    public boolean hasAllergen(String allergen) {
        return allergens.getOrDefault(allergen.toLowerCase(), false);
    }

    public boolean addAllergen(String allergen, boolean value) {
        return allergens.put(allergen.toLowerCase(), value);
    }

    public void addAllergens(Map<String, Boolean> allergens) {
        this.allergens.putAll(allergens);
    }

}
