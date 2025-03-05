package midearth;

public class Wizard extends MiddleEarthCharacter{
	
	public Wizard(String name, double health, double power) {
		super(name, health, power);
	}
	
	@Override
	// no damage to same character
	public boolean attack(MiddleEarthCharacter target) {
		if (target instanceof Wizard) {
			return false;
		}
		
		double damage = this.power;
		
		// 1.5x damage to dwarf
		if (target instanceof Dwarf) {
			damage *= 1.5;
		}
		// no damage to human
		else if (target instanceof Human) {
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
		return "Wizard";
	}

}
