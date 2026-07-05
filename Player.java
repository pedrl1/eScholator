public class Player extends Character {
    private int level;
    private int experience;

    public Player(String name, int maxHealth, int level, int experience) {
        super(name, maxHealth, 100, 50); 
        this.level = 1;
        this.experience = 0;
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
            setDamage(getDamage() + 20);
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
        if (amount <= experience) {
            experience -= amount;
        }
    }
}
