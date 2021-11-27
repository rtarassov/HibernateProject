package menu;

import model.Animal;
import persistence.RepositoryAnimal;
import persistence.RepositoryCage;

import java.time.LocalDate;
import java.util.Scanner;

public class AnimalMenu {

    RepositoryAnimal repositoryAnimal = new RepositoryAnimal();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add animals");
        System.out.println("2: Remove animals");
        System.out.println("3: Update animal by ID");
        System.out.println("4: List all animals");
        System.out.println("5: Find animal by ID");
        System.out.println("6: Assign animal to cage");
        System.out.println("7: List animals by cage");
        System.out.println("8: Number of animals in cage");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    addAnimalMenu(input, repositoryAnimal);
                    break;
                case 2:
                    deleteAnimalMenu(input);
                    break;
                case 3:
                    updateAnimalGrowthStageMenu(input);
                    break;
                case 4:
                    listAllAnimalsMenu(repositoryAnimal);
                    break;
                case 5:
                    findAnimalByIdMenu(input);
                    break;
                case 6:
                    assignAnimalToCage(input);
                    break;
                case 7:
                    listAnimalsByCage();
                    break;
                case 8:
                    getAnimalsInCage(input);
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
    }

    public void addAnimalMenu(Scanner input, RepositoryAnimal repositoryAnimal) {
        input.nextLine();
        System.out.print("Animal type: ");
        String type = input.nextLine();

        String growthStage;
        boolean isValidGrowthStage = false;

        do {
            System.out.println("Animal growth stages: [ Baby / Child / Adult / Elder ]");
            System.out.print("Animal growth stage: ");
            growthStage = input.nextLine();
            switch (growthStage) {
                case "Baby":
                    growthStage = "Baby";
                    isValidGrowthStage = true;
                    break;
                case "Child":
                    growthStage = "Child";
                    isValidGrowthStage = true;
                    break;
                case "Adult":
                    growthStage = "Adult";
                    isValidGrowthStage = true;
                    break;
                case "Elder":
                    growthStage = "Elder";
                    isValidGrowthStage = true;
                    break;
                default:
                    System.out.println("Not a valid growth stage");
                    break;
            }
        } while (!isValidGrowthStage);

        LocalDate dateOfRegister = LocalDate.now();
        System.out.println("Animal date of register: " + dateOfRegister);

        Animal animal = new Animal(type, growthStage, dateOfRegister);
        repositoryAnimal.addAnimal(animal);
    }

    public void updateAnimalGrowthStageMenu(Scanner input) {
        input.nextLine();
        int animalId;
        while (true) {
            System.out.print("Enter animal id to update: ");
            String animalIdString = input.nextLine();
            if (animalIdString.matches("^\\d*$")) {
                animalId = Integer.parseInt(animalIdString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        if (repositoryAnimal.findAnimalById(animalId) == null) {
            System.out.println("Didn't find a cage with ID: " + animalId);
        } else {
            String currentStage = repositoryAnimal.findAnimalById(animalId).getAnimalGrowthStage();
            String growthStage;
            switch (currentStage) {
                case "Baby":
                    growthStage = "Child";
                    repositoryAnimal.updateAnimalGrowthStage(animalId, growthStage);
                    System.out.println("Animal is now: " + repositoryAnimal.findAnimalById(animalId).getAnimalGrowthStage());
                    break;
                case "Child":
                    growthStage = "Adult";
                    repositoryAnimal.updateAnimalGrowthStage(animalId, growthStage);
                    System.out.println("Animal is now: " + repositoryAnimal.findAnimalById(animalId).getAnimalGrowthStage());
                    break;
                case "Adult":
                    growthStage = "Elder";
                    repositoryAnimal.updateAnimalGrowthStage(animalId, growthStage);
                    System.out.println("Animal is now: " + repositoryAnimal.findAnimalById(animalId).getAnimalGrowthStage());
                    break;
                case "Elder":
                    System.out.println("After Elder animal dies.");
                    System.out.print("Is animal dead? [Y / N]: ");
                    String isDead = input.nextLine();
                    if (isDead.contains("Y")) {
                        Animal animal = repositoryAnimal.findAnimalById(animalId);
                        repositoryAnimal.deleteAnimal(animal);
                    } else {
                        System.out.println("Animal is: " + currentStage);
                        break;
                    }
            }
        }
    }

    public void findAnimalByIdMenu(Scanner input) {
        input.nextLine();
        int animalId;
        while (true) {
            System.out.print("Enter animal ID to find: ");
            String cageIdString = input.nextLine();
            if (cageIdString.matches("^\\d*$")) {
                animalId = Integer.parseInt(cageIdString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        System.out.println(repositoryAnimal.findAnimalById(animalId));
    }

    // Method to put animal in a cage.

    public void listAllAnimalsMenu(RepositoryAnimal repositoryAnimal) {
        System.out.println(repositoryAnimal.listAllAnimals());
    }

    public void deleteAnimalMenu(Scanner input) {
        input.nextLine();
        int animalId;
        while (true) {
            System.out.print("Enter animal ID to delete: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                animalId = Integer.parseInt(idString);
                if (repositoryAnimal.findAnimalById(animalId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        Animal animal = repositoryAnimal.findAnimalById(animalId);
        repositoryAnimal.deleteAnimal(animal);
    }

    public void assignAnimalToCage(Scanner input) {
        input.nextLine();
        RepositoryCage repositoryCage = new RepositoryCage();

        int animalId;

        while (true) {
            System.out.print("Enter animal ID to assign Cage to: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                animalId = Integer.parseInt(idString);
                if (repositoryAnimal.findAnimalById(animalId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }

        int cageId;
        while (true) {
            System.out.print("Enter cage ID to assign: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                cageId = Integer.parseInt(idString);
                if (repositoryCage.findCageById(cageId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        repositoryAnimal.assignAnimaltoCage(animalId, cageId);
    }

    public void listAnimalsByCage() {
        System.out.println(repositoryAnimal.listAnimalsByCage());
    }

    public int getAnimalsInCage(Scanner input) {
        input.nextLine();
        int cageId = 0;
        System.out.println("Enter cage ID: ");
        String idString = input.nextLine();
        if (idString.matches("^\\d*$")) {
            cageId = Integer.parseInt(idString);
        }
        System.out.println("Animals in cage: " + repositoryAnimal.getAnimalsInCage(cageId));
        return repositoryAnimal.getAnimalsInCage(cageId);
    }
}
