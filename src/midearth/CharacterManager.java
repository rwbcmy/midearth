package midearth;

public class CharacterManager {

	private MiddleEarthCharacter[] roster;
	private int count;
	
	public CharacterManager() {
		roster = new MiddleEarthCharacter[5];
		count = 0;
	}
	
	public boolean addCharacter(MiddleEarthCharacter toAdd) {
		if (toAdd == null) {
			return false;
		}
		if (count >= roster.length) {
			expandRoster();
		}
		roster[count] = toAdd;
		count++;
		return true;
	}
	
	private void expandRoster() {
		MiddleEarthCharacter[] bigger = new MiddleEarthCharacter[roster.length * 2];
		for (int i = 0; i < roster.length; i++) {
			bigger[i] = roster[i];
		}
		roster = bigger;
	}
	
	public MiddleEarthCharacter getCharacter(String name) {
		if (name == null) {
			return null;
		}
		for (int i = 0; i < count; i++) {
			if (roster[i].getName().equalsIgnoreCase(name)) {
				return roster[i];
			}
		}
		return null;
	}
	
	public boolean updateCharacter(MiddleEarthCharacter character, String newName, double newHealth, double newPower) {
		if (character == null) {
			return false;
		}
		
		boolean found = false;
		for (int i = 0; i < count; i++) {
			if (roster[i] == character) {
				found = true;
				break;
			}
		}
		if (!found) {
			return false;
		}
		
		boolean changed = false;
		
		if (newName != null && !newName.equals(character.getName())) {
			character.setName(newName);
			changed = true;
		}
		if (character.getHealth() != newHealth) {
			character.setHealth(newHealth);
			changed = true;
		}
		if (character.getPower() != newPower) {
			character.setPower(newPower);
			changed = true;
		}
		return changed;
	}
	
    public boolean deleteCharacter(MiddleEarthCharacter toDelete) {
        if (toDelete == null) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (roster[i] == toDelete) {
                for (int j = i; j < count - 1; j++) {
                    roster[j] = roster[j + 1];
                }
                roster[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }
    
    public void displayAllCharacters() {
        if (count == 0) {
            System.out.println("No characters stored.");
            return;
        }
        for (int i = 0; i < count; i++) {
            roster[i].displayInfo();
            System.out.println("------------");
        }
    }
}
