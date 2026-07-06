public class PenAbility extends Ability implements Power {

    public PenAbility(String name, String description, int cost) {
        super(name, description, cost, 3);
    }

    @Override
    public void abilityPower(Player player, int exp) {
        if (exp < getCost()) {
            return;
        } else {
            player.setDamage(player.getDamage() + (int)(0.75 * exp));
            player.spendExp(getCost());
        }
    }
}