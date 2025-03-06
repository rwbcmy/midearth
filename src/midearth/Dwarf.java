package midearth;

public class Dwarf extends MiddleEarthCharacter{
	
	public Dwarf(String name, double health, double power) {
		super(name, health, power);
	}
	
	@Override
	// no damage to same character
	public boolean attack(MiddleEarthCharacter target) {
		if (target instanceof Dwarf) {
			return false;
		}
		
		double damage = this.power;
		
		// 1.5x damage to elf
		if (target instanceof Elf) {
			damage *= 1.5;
		}
		// no damage to wizard
		else if (target instanceof Wizard) {
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
		return "Dwarf";
	}

}
