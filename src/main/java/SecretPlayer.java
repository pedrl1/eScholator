public class SecretPlayer extends Player {
    public SecretPlayer(String name, int health, int damage, int exp) {
        super(name, 200, 1, 0);
        setDamage(150);
    }

    public void habilidadeUltraSecreta(){
        setDamage(getDamage() * 2);
    }
    
}
