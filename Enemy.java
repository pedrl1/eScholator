public class Enemy extends Character {

    public Enemy(String name, int maxHealth, int attack, int defense) {
        super(name, maxHealth, attack, defense);
    }

    private static Character[] enemy = {
        
        new Enemy("Lil Kid", 150, 50, 50), // very easy normal enemy
        new Enemy("High Schooler", 200, 75, 25), // very easy boss

        new Enemy("College Student", 250, 100, 100), // easy normal enemy 
        new Enemy("Mentor", 300, 125, 75), // easy boss

        new Enemy("Undergraduate", 350, 150, 150), // medium normal enemy
        new Enemy("Graduate", 400, 200, 100), // medium boss

        new Enemy("Senior", 500, 250, 250), // hard normal enemy
        new Enemy("Master", 1000, 500, 200), // hard boss

        new Enemy("Doctor", 1500, 750, 750), // very hard normal enemy
        new Enemy("Professor", 2000, 1000, 300) // very hard boss
    };

    public static Character getEnemy(int index) {
        if (index >= 0 && index < enemy.length) {
            return enemy[index];
        }
        return null; 
    }

}
