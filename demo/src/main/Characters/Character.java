public abstract class Character {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int defense;

    public Character(String name, int maxHealth, int attack, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }
    
    public int getAttack() {
        return attack;
    }   

    public int getDefense() {
        return defense;
    }

    public int getCurrentHealth() {
        return currentHealth;  
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }
    
    public void heal(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }
}
