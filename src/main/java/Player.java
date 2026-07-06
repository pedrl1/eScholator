public class Player extends Character {
    private int level;
    private int experience;

    public Player(String name, int maxHealth, int level, int experience) {
        super(name, maxHealth, 1000, 50);
        this.level = level;
        this.experience = experience;
    }

    public void gainExperience(int amount) {
        experience += amount;
        checkLevelUp();
    }

    private void checkLevelUp() {
        while (experience >= 100) {
            level++;
            experience -= 100;
            setMaxHealth(getMaxHealth() + 50);
            setDamage(getDamage() + 200);
        }
    }

    public int getLevel() { 
        return level; 
    }

    public int getExperience() { 
        return experience; 
    }
    
    public int getDamage() { 
        return getAttack(); 
    }

    public void setDamage(int damage) { 
        setAttack(damage); 
    }

    public void setExperience(int experience) { 
        this.experience = experience; 
    }

    public void spendExp(int amount) { 
        if (amount <= experience) 
            experience -= amount; 
    }
}