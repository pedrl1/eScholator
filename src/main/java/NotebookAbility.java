public class NotebookAbility extends Ability implements Power {

    public NotebookAbility (String name, String description, int cost) {
        super(name, description, cost,3);
    }
    
    @Override
    public void abilityPower(Player player, int exp) {
        if (exp < cost){
            return;
        }
        else {
            player.setDamage(player.getDamage() + exp);
            player.spendExp(cost);
        }
    }
    
}
