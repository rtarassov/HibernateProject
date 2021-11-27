package menu;

import model.Cage;
import persistence.RepositoryCage;

import java.util.Scanner;

public class CageMenu {
    RepositoryCage repositoryCage;

    public CageMenu() {
        repositoryCage = new RepositoryCage();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add cages");
        System.out.println("2: Remove cages");
        System.out.println("3: Update cage by ID");
        System.out.println("4: List all cages");
        System.out.println("5: Find cage by ID");
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
                    addCageMenu(input);
                    break;
                case 2:
                    removeCage(input);
                    break;
                case 3:
                    updateCageSizeById(input);
                    break;
                case 4:
                    listCages();
                    break;
                case 5:
                    findCageByIdMenu(input);
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

    public void addCageMenu(Scanner input) {
        input.nextLine();
        String type;
        do {
            System.out.println("Cage type only has letters from a-Z");
            System.out.print("Cage type: ");
            type = input.nextLine();
        } while (!type.matches("[a-zA-Z]+"));

        int size;
        while (true) {
            System.out.print("Cage size: ");
            String cageSizeString = input.nextLine();
            if (cageSizeString.matches("^\\d*$")) {
                size = Integer.parseInt(cageSizeString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        Cage cage = new Cage(type, size);
        repositoryCage.addCage(cage);
    }

    public void removeCage(Scanner input) {
        input.nextLine();
        int cageId;
        while (true) {
            System.out.print("Enter cage ID to delete: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                cageId = Integer.parseInt(idString);
                if (repositoryCage.findCageById(cageId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        Cage cage = repositoryCage.findCageById(cageId);
        repositoryCage.removeCage(cage);
    }

    public void updateCageSizeById(Scanner input) {
        input.nextLine();
        int cageId;
        while (true) {
            System.out.print("Enter cage id to update: ");
            String cageIdString = input.nextLine();
            if (cageIdString.matches("^\\d*$")) {
                cageId = Integer.parseInt(cageIdString);
                if (repositoryCage.findCageById(cageId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }

            int newCageSize;
            while (true) {
                System.out.println("Enter new size: ");
                String cageSizeString = input.nextLine();
                if (cageSizeString.matches("^\\d*$")) {
                    newCageSize = Integer.parseInt(cageSizeString);
                    break;
                }
                System.out.println("Try again, enter only numbers.");
            }
            repositoryCage.updateCageSize(cageId, newCageSize);
    }

    public void findCageByIdMenu(Scanner input) {
        input.nextLine();
        int cageId;
        while (true) {
            System.out.print("Enter cage ID to find: ");
            String cageIdString = input.nextLine();
            if (cageIdString.matches("^\\d*$")) {
                cageId = Integer.parseInt(cageIdString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        System.out.println(repositoryCage.findCageById(cageId));
    }

    public void listCages() {
        System.out.println(repositoryCage.listAllCages());
    }


}