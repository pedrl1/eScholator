public abstract class Ability {

    protected String name;
    protected String description;
    protected int cost;
    private int maxUses;
    private int numUses;
    
    public Ability(String name, String description, int cost, int maxUses) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.maxUses = maxUses;
        this.numUses = 0;
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

    public int getMaxUses() {
        return maxUses;
    }

    public int getRemainingUses() {
        return maxUses - numUses;
    }

    public boolean canUse() {
        return getRemainingUses() > 0;
    }
    public boolean useAbility() {
        if (getRemainingUses() > 0) {
            numUses++;
            return true;
        }
        return false;
    }
}
