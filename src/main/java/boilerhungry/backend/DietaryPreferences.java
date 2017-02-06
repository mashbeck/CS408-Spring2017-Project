package boilerhungry.backend;

/**
 * Created by eric on 2/6/17.
 */
public class DietaryPreferences {

    private boolean vegetarian;
    private boolean noEggs;
    private boolean noFish;
    private boolean noGluten;
    private boolean noMilk;
    private boolean noPeanuts;
    private boolean noShellfish;
    private boolean noSoy;
    private boolean noWheat;

    public DietaryPreferences(boolean vegetarian, boolean noEggs, boolean noFish, boolean noGluten, boolean noMilk, boolean noPeanuts, boolean noShellfish, boolean noSoy, boolean noWheat) {
        this.vegetarian = vegetarian;
        this.noEggs = noEggs;
        this.noFish = noFish;
        this.noGluten = noGluten;
        this.noMilk = noMilk;
        this.noPeanuts = noPeanuts;
        this.noShellfish = noShellfish;
        this.noSoy = noSoy;
        this.noWheat = noWheat;
    }

    public DietaryPreferences() {
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isNoEggs() {
        return noEggs;
    }

    public void setNoEggs(boolean noEggs) {
        this.noEggs = noEggs;
    }

    public boolean isNoFish() {
        return noFish;
    }

    public void setNoFish(boolean noFish) {
        this.noFish = noFish;
    }

    public boolean isNoGluten() {
        return noGluten;
    }

    public void setNoGluten(boolean noGluten) {
        this.noGluten = noGluten;
    }

    public boolean isNoMilk() {
        return noMilk;
    }

    public void setNoMilk(boolean noMilk) {
        this.noMilk = noMilk;
    }

    public boolean isNoPeanuts() {
        return noPeanuts;
    }

    public void setNoPeanuts(boolean noPeanuts) {
        this.noPeanuts = noPeanuts;
    }

    public boolean isNoShellfish() {
        return noShellfish;
    }

    public void setNoShellfish(boolean noShellfish) {
        this.noShellfish = noShellfish;
    }

    public boolean isNoSoy() {
        return noSoy;
    }

    public void setNoSoy(boolean noSoy) {
        this.noSoy = noSoy;
    }

    public boolean isNoWheat() {
        return noWheat;
    }

    public void setNoWheat(boolean noWheat) {
        this.noWheat = noWheat;
    }

}
