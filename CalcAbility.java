public class CalcAbility extends Ability implements Power {

    public CalcAbility(String name, String description, int cost) {
        super(name, description, cost);
    }

    @Override
    public void abilityPower(Player player, int exp) {
        if (exp < cost){
            return;
        } 
        else {
            player.setDamage(player.getDamage() + (int)(1.25 * exp));
            player.spendExp(cost);
        }
    }
}
