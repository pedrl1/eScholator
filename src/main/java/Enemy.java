public class Enemy extends Character {
    private int difficulty;

    public Enemy(String name, int maxHealth, int attack, int defense, int difficulty) {
        super(name, maxHealth, attack, defense);
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private static Enemy[] enemyList = {
        new Enemy("Lil Kid",60,  20, 10, 1),
        new Enemy("High Schooler",60,  25, 15, 1),
        new Enemy("College Student",120, 30, 20, 2),
        new Enemy("Mentor",120, 35, 25, 2),
        new Enemy("Undergraduate",180, 40, 30, 3),
        new Enemy("Graduate",180, 45, 35, 3),
        new Enemy("Senior",240, 50, 40, 4),
        new Enemy("Master",240, 55, 45, 4),
        new Enemy("Doctor",300, 60, 50, 5),
        new Enemy("Professor",300, 65, 55, 5)
    };

    public static Enemy getEnemy(int index) {
        if (index >= 0 && index < enemyList.length) return enemyList[index];
        return null;
    }
}