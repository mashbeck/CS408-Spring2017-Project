package boilerhungry.backend;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFood {

    private String name;
    private List<ItemAppearance> appearances;

    public UpcomingFood(String name) {
        this.name = name;
        this.appearances = new ArrayList<>();
    }

    public void addAppearance(ItemAppearance appearance) {
        appearances.add(appearance);
    }

    public List<ItemAppearance> getAppearances() {
        return appearances;
    }

    public String getName() {
        return name;
    }
}
