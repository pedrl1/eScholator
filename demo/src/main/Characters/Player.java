public class Player extends Character {
    private int level;
    private int experience;

    public Player(String name, int maxHealth, int level, int experience) {
        super(name, maxHealth, 100, 50); 
        this.level = level;
        this.experience = experience;
    }

    public void gainExperience(int amount) {
        experience += amount;
        checkLevelUp();
    }

    private void checkLevelUp() {
        if (experience >= 100) {
            level++;
            experience -= 100;
            System.out.println(getName() + " leveled up to " + level + "!");
        }
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
