public class NotebookAbility extends Ability implements Power {

    public NotebookAbility (String name, String description, int cost) {
        super(name, description, cost);
    }
    
    @Override
    public void abilityPower(int exp) {
        System.out.println("Notebook ability activated!");
        Player player = new Player();

        player.setDamage(player.getDamage() + exp);
    }
    
}
