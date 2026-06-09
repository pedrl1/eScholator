public class CalcAbility extends Ability implements Power {

    public CalcAbility(String name, String description, int cost) {
        super(name, description, cost);
    }

    @Override
    public void abilityPower(int exp) {

        if (exp < cost){
            System.out.println("Not enough experience to use this ability.");
            return;
        } 
        else {
            System.out.println("Calculator ability activated!");
            Player player = new Player();
            player.setDamage(player.getDamage() + (1.25 * exp));
        }
    }
        
        
    
    
}
