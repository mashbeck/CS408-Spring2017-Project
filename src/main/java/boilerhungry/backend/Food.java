package boilerhungry.backend;

/**
 * Created by Matthew on 2/6/2017.
 */
public class Food {
    String name;
    private boolean isVegetarian;
    private boolean eggs;
    private boolean fish;
    private boolean gluten;
    private boolean milk;
    private boolean peanuts;
    private boolean shellfish;
    private boolean soy;
    private boolean treeNuts;
    private boolean wheat;

    public Food(String name, boolean isVegetarian, boolean eggs, boolean fish, boolean gluten, boolean milk,
                boolean peanuts, boolean shellfish, boolean soy, boolean treeNuts, boolean wheat) {
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.eggs = eggs;
        this.fish = fish;
        this.gluten = gluten;
        this.milk = milk;
        this.peanuts = peanuts;
        this.shellfish = shellfish;
        this.soy = soy;
        this.treeNuts = treeNuts;
        this.wheat = wheat;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isEggs() {
        return eggs;
    }

    public boolean isFish() {
        return fish;
    }

    public boolean isGluten() {
        return gluten;
    }

    public boolean isMilk() {
        return milk;
    }

    public boolean isPeanuts() {
        return peanuts;
    }

    public boolean isShellfish() {
        return shellfish;
    }

    public boolean isSoy() {
        return soy;
    }

    public boolean isTreeNuts() {
        return treeNuts;
    }

    public boolean isWheat() {
        return wheat;
    }
}
