package menu;

import model.Zoo;
import persistence.RepositoryZoo;

import java.util.Scanner;

public class ZooMenu {

    RepositoryZoo repositoryZoo = new RepositoryZoo();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add Zoo");
        System.out.println("2: Remove Zoo");
        System.out.println("3: Update Zoo by ID");
        System.out.println("4: List all Zoos");
        System.out.println("5: Find Zoo by ID");
        System.out.println("6: Assign Zoo");
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
                    addZooMenu(input);
                    break;
                case 2:
                    deleteZooMenu(input);
                    break;
                case 3:
                    updateZoo(input);
                    break;
                case 4:
                    listAllZoosMenu();
                    break;
                case 5:
                    findZooById(input);
                    break;
                case 6:
                    assignZooToCustomerMenu(input);
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

    public void addZooMenu(Scanner input) {
        input.nextLine();
        String zooLocation;
        do {
            System.out.println("Zoo only has letters from a-Z and no space");
            System.out.print("Zoo location: ");
            zooLocation = input.nextLine();
        } while (!zooLocation.matches("[a-zA-Z]+"));

        String zooName;

        do {
            System.out.println("Name only has letters from a-Z");
            System.out.print("Customer name: ");
            zooName = input.nextLine();
        } while (!zooName.matches("[a-zA-Z]+"));

        Zoo zoo = new Zoo(zooLocation, zooName);
        repositoryZoo.addZoo(zoo);
    }

    public void findZooById(Scanner input) {
        input.nextLine();
        int zooId;
            while (true) {
                System.out.println("Enter Zoo ID: ");
                String idString = input.nextLine();
                if (idString.matches("^\\d*$")) {
                    zooId = Integer.parseInt(idString);
                    if (repositoryZoo.findZooById(zooId) != null) {
                        break;
                    }
                }
                System.out.println("Try again, enter only numbers.");
            }
            System.out.println(repositoryZoo.findZooById(zooId));
    }

    public void listAllZoosMenu() {
        System.out.println(repositoryZoo.listAllZoos());
    }

    public void deleteZooMenu(Scanner input) {
        input.nextLine();
        int zooId;

        while (true) {
            System.out.println("Enter Ticket ID: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                zooId = Integer.parseInt(idString);
                if (repositoryZoo.findZooById(zooId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        Zoo zoo = repositoryZoo.findZooById(zooId);
        repositoryZoo.deleteZoo(zoo);
    }

    public void assignZooToCustomerMenu(Scanner input) {
        input.nextLine();
        int customerId;

        while (true) {
            System.out.print("Enter customer ID to assign Zoo to: ");
            String customerIdString = input.nextLine();
            if (customerIdString.matches("^\\d*$")) {
                customerId = Integer.parseInt(customerIdString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }

        int zooId;

        while (true) {
            System.out.print("Enter Zoo ID to give: ");
            String zooIdString = input.nextLine();
            if (zooIdString.matches("^\\d*$")) {
                zooId = Integer.parseInt(zooIdString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        repositoryZoo.assignZooToCustomer(customerId, zooId);
    }

    public void updateZoo(Scanner input) {
        input.nextLine();
        int zooId;
        boolean endLoop = false;


            while (true) {
                System.out.print("Enter Zoo ID to update: ");
                String idString = input.nextLine();
                if (idString.matches("^\\d*$")) {
                    zooId = Integer.parseInt(idString);
                    if (repositoryZoo.findZooById(zooId) != null) {
                        break;
                    }
                }
                System.out.println("Try again, enter only numbers.");
            }
        do {
            System.out.println("What do you want to update? [ Name / Location]");
            System.out.print("Update: ");
            String choice = input.nextLine();

            switch (choice) {
                case "Name":
                    String newZooName;
                    do {
                        System.out.println("Name only has letters from a-Z");
                        System.out.print("Enter new name: ");
                        newZooName = input.nextLine();
                    } while (!newZooName.matches("[a-zA-Z]+"));
                    repositoryZoo.updateZooName(newZooName, zooId);
                    endLoop = true;
                    break;
                case "Location":

                    String newZooLocation;

                    do {
                        System.out.println("Location only has letters from a-Z");
                        System.out.print("Enter new location: ");
                        newZooLocation = input.nextLine();
                    } while (!newZooLocation.matches("[a-zA-Z]+"));
                    repositoryZoo.updateZooLocation(newZooLocation, zooId);
                    endLoop = true;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while (!endLoop);
    }
}
