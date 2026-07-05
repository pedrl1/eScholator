public abstract class Ability {

    protected String name;
    protected String description;
    protected int cost;
    
    public Ability(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public int getCost() {
        return cost;
    }
}
