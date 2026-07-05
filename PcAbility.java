public class PcAbility extends Ability implements Power {

    public PcAbility(String name, String description, int cost) {
        super(name, description, cost);
    }

    @Override
    public void abilityPower(Player player, int exp) {
        if (exp < cost){
            return;
        } 
        else {
            player.setDamage(player.getDamage() + (2 * exp));
            player.spendExp(cost);
        }
    }
}
