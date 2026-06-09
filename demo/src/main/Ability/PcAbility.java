public class PcAbility extends Ability implements Power {

    public PcAbility(String name, String description, int cost) {
        super(name, description, cost);
    }

    @Override
    public void abilityPower(int exp) {
        System.out.println("PC ability activated!");
        Player player = new Player();

        player.setDamage(player.getDamage() + (2 * exp));
    }
    
}
