package midearth;

public class Human extends MiddleEarthCharacter{
	
	public Human(String name, double health, double power) {
		super(name, health, power);
	}
	
	@Override
	// no damage to same character
	public boolean attack(MiddleEarthCharacter target) {
		if (target instanceof Human) {
			return false;
		}
		
		double damage = this.power;
		
		// 1.5x damage to wizard
		if (target instanceof Wizard) {
			damage *= 1.5;
		}
		// no damage to orc
		else if (target instanceof Orc) {
			damage = 0.0;
		}
		
		
		
		double originalHealth = target.getHealth();
		double newHealth = originalHealth - damage;
		if (newHealth < 0) newHealth = 0;
		target.setHealth(newHealth);;
		
		return (damage > 0);
	}
	
	@Override
	public String getRace() {
		return "Human";
	}

}
