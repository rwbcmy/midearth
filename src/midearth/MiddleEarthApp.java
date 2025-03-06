package midearth;

import java.util.Scanner;

public class MiddleEarthApp {
    public static void main(String[] args) {
        MiddleEarthCouncil council = MiddleEarthCouncil.getInstance();
        CharacterManager manager = council.getCharacterManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("Middle-Earth Menu");
            System.out.println("1. Add a character");
            System.out.println("2. View characters");
            System.out.println("3. Update characters");
            System.out.println("4. Delete characters");
            System.out.println("5. Simulate an attack");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            
            int option = -1;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            
            switch (option) {
                case 1:
                    addCharacter(manager, scanner);
                    break;
                case 2:
                    manager.displayAllCharacters();
                    break;
                case 3:
                    updateCharacter(manager, scanner);
                    break;
                case 4:
                    deleteCharacter(manager, scanner);
                    break;
                case 5:
                    simulateAttacks(manager);
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Please enter a number 1 through 6.");
            }
        }
        scanner.close();
    }
    
    private static void addCharacter(CharacterManager mgr, Scanner sc) {
        System.out.print("Enter race (Elf, Dwarf, Human, Orc, Wizard): ");
        String race = sc.nextLine().trim();
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter health: ");
        double health = Double.parseDouble(sc.nextLine());
        System.out.print("Enter power: ");
        double power = Double.parseDouble(sc.nextLine());
        
        MiddleEarthCharacter character;
        if (race.equalsIgnoreCase("elf")) {
            character = new Elf(name, health, power);
        } else if (race.equalsIgnoreCase("dwarf")) {
            character = new Dwarf(name, health, power);
        } else if (race.equalsIgnoreCase("human")) {
            character = new Human(name, health, power);
        } else if (race.equalsIgnoreCase("orc")) {
            character = new Orc(name, health, power);
        } else if (race.equalsIgnoreCase("wizard")) {
            character = new Wizard(name, health, power);
        } else {
            System.out.println("Race not recognized. Character not added.");
            return;
        }
        
        if (mgr.addCharacter(character)) {
            System.out.println("Character added.");
        } else {
            System.out.println("Error: Could not add character.");
        }
    }
    
    private static void updateCharacter(CharacterManager mgr, Scanner sc) {
        System.out.print("Enter the name of the character to update: ");
        String name = sc.nextLine().trim();
        MiddleEarthCharacter ch = mgr.getCharacter(name);
        if (ch == null) {
            System.out.println("Character not found.");
            return;
        }
        System.out.print("Enter new name (or press Enter to keep \"" + ch.getName() + "\"): ");
        String newName = sc.nextLine().trim();
        if (newName.isEmpty()) {
            newName = ch.getName();
        }
        System.out.print("Enter new health: ");
        double newHealth = Double.parseDouble(sc.nextLine());
        System.out.print("Enter new power: ");
        double newPower = Double.parseDouble(sc.nextLine());
        
        if (mgr.updateCharacter(ch, newName, newHealth, newPower)) {
            System.out.println("Character updated.");
        } else {
            System.out.println("No changes made.");
        }
    }
    
    private static void deleteCharacter(CharacterManager mgr, Scanner sc) {
        System.out.print("Enter the name of the character to delete: ");
        String name = sc.nextLine().trim();
        MiddleEarthCharacter ch = mgr.getCharacter(name);
        if (ch == null) {
            System.out.println("Character not found.");
            return;
        }
        if (mgr.deleteCharacter(ch)) {
            System.out.println("Character deleted.");
        } else {
            System.out.println("Error deleting character.");
        }
    }
    
    private static void simulateAttacks(CharacterManager mgr) {
        int total = mgr.getCount();
        if (total < 2) {
            System.out.println("Not enough characters to simulate attacks.");
            return;
        }
        MiddleEarthCharacter[] allChars = mgr.getAllCharacters();
        System.out.println("Simulating attacks:");
        for (int i = 0; i < allChars.length; i++) {
            for (int j = 0; j < allChars.length; j++) {
                if (i == j) continue;
                boolean hit = allChars[i].attack(allChars[j]);
                if (hit) {
                    System.out.println(allChars[i].getName() + " hit " + allChars[j].getName());
                } else {
                    System.out.println(allChars[i].getName() + " failed to hit " + allChars[j].getName());
                }
            }
        }
        System.out.println("Attack simulation complete.");
    }
}
