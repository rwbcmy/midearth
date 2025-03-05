package midearth;

public class Elf extends MiddleEarthCharacter{
	
	public Elf(String name, double health, double power) {
		super(name, health, power);
	}
	
	@Override
	// no damage to same character
	public boolean attack(MiddleEarthCharacter target) {
		if (target instanceof Elf) {
			return false;
		}
		
		double damage = this.power;
		
		// 1.5x damage to orc
		if (target instanceof Orc) {
			damage *= 1.5;
		}
		// no damage to dwarf
		else if (target instanceof Dwarf) {
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
		return "Elf";
	}

}
