public class PenAbility extends Ability implements Power {

    public PenAbility(String name, String description, int cost) {
        super(name, description, cost);
    }

    @Override
    public void abilityPower(int exp) {
        System.out.println("Pen ability activated!");
        Player player = new Player();

        player.setDamage(player.getDamage() + (0.75 * exp));
    }
    
}
