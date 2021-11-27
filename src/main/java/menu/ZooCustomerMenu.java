package menu;

import model.Ticket;
import model.ZooCustomer;
import persistence.RepositoryZooCustomer;

import java.util.Scanner;

public class ZooCustomerMenu {

    RepositoryZooCustomer repositoryZooCustomer = new RepositoryZooCustomer();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add ZooCustomer");
        System.out.println("2: Remove ZooCustomer");
        System.out.println("3: Update ZooCustomer by ID");
        System.out.println("4: List all ZooCustomers");
        System.out.println("5: Find ZooCustomer by ID");
        System.out.println("6: Number of customers");
        System.out.println("7: List Customers by Zoo");
        System.out.println("8: Most common ticket");
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
                    addZooCustomerMenu(input);
                    break;
                case 2:
                    deleteCustomerMenu(input);
                    break;
                case 3:
                    updateZooCustomer(input);
                    break;
                case 4:
                    listAllCustomersMenu();
                    break;
                case 5:
                    findZooCustomerByIdMenu(input);
                    break;
                case 6:
                    numberOfCustomers();
                    break;
                case 7:
                    listCustomersByZooName();
                    break;
                case 8:
                    getMostCommonTicket();
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

    public void addZooCustomerMenu(Scanner input) {
        input.nextLine();
        String customerType;
        boolean isValidCustomerType;

        do {
            System.out.println("Customer types: [ Bronze / Silver / Gold ]");
            System.out.print("Customer type: ");
            customerType = input.nextLine();
            switch (customerType) {
                case "Bronze":
                    customerType = "Bronze";
                    isValidCustomerType = true;
                    break;
                case "Silver":
                    customerType = "Silver";
                    isValidCustomerType = true;
                    break;
                case "Gold":
                    customerType = "Gold";
                    isValidCustomerType = true;
                    break;
                default:
                    System.out.println("Not a valid Customer type.");
                    isValidCustomerType = false;
                    break;
            }
        } while (!isValidCustomerType);

        String name;

        do {
            System.out.println("Name only has letters from a-Z");
            System.out.print("Customer name: ");
            name = input.nextLine();
        } while (!name.matches("[a-zA-Z]+"));

        boolean validOption = false;
        boolean isClientNew = false;
        String isNew;
        do {
            System.out.print("Is the client new? [ Y / N ]: ");
            isNew = input.nextLine();
            switch (isNew) {
                case "Y":
                    isClientNew = true;
                    validOption = true;
                    break;
                case "N":
                    validOption = true;
                    break;
                default:
                    System.out.println("Press Y / N");
                    break;
            }
        } while (!validOption);

        ZooCustomer zooCustomer = new ZooCustomer(customerType, name, isClientNew);
        repositoryZooCustomer.addZooCustomer(zooCustomer);
    }


        public void findZooCustomerByIdMenu(Scanner input) {
        input.nextLine();
        int customerId;
        while (true) {
            System.out.print("Enter Customer ID: ");
            String customerIdString = input.nextLine();
            if (customerIdString.matches("^\\d*$")) {
                customerId = Integer.parseInt(customerIdString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        System.out.println(repositoryZooCustomer.findZooCustomerById(customerId));
    }

    public void listAllCustomersMenu() {
        System.out.println(repositoryZooCustomer.listAllCustomers());
    }

    public void deleteCustomerMenu(Scanner input) {
        input.nextLine();
        int customerId;
        while (true) {
            System.out.println("Enter customer ID to delete: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                customerId = Integer.parseInt(idString);
                if (repositoryZooCustomer.findZooCustomerById(customerId) == null) {
                    System.out.println("Didn't find a customer with ID: " + customerId);
                } else {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");

        }
        ZooCustomer zooCustomer = repositoryZooCustomer.findZooCustomerById(customerId);
        repositoryZooCustomer.deleteZooCustomer(zooCustomer);
    }

    public void numberOfCustomers() {
        System.out.println("Number of customers: " + repositoryZooCustomer.listAllCustomers().size());
    }

    public void updateZooCustomer(Scanner input) {
        input.nextLine();
        int zooCustomerId;

        System.out.println("What do you want to update? [ Name / Type / Status ]");
        System.out.print("Update: ");
        String choice = input.nextLine();
        switch (choice) {
            case "Name":
                repositoryZooCustomer.listAllCustomers();
                while (true) {
                    System.out.print("Enter Customer ID to update: ");
                    String idString = input.nextLine();
                    if (idString.matches("^\\d*$")) {
                        zooCustomerId = Integer.parseInt(idString);
                        if (repositoryZooCustomer.findZooCustomerById(zooCustomerId) != null) {
                            break;
                        }
                    }
                    System.out.println("Try again, enter only numbers.");
                }
                String newCustomerName;
                do {
                    System.out.println("Name only has letters from a-Z");
                    System.out.print("Enter new name: ");
                    newCustomerName = input.nextLine();
                } while (!newCustomerName.matches("[a-zA-Z]+"));
                repositoryZooCustomer.updateZooCustomerName(newCustomerName, zooCustomerId);
                break;

            case "Type":
                repositoryZooCustomer.listAllCustomers();
                while (true) {
                    System.out.print("Enter Customer ID to update: ");
                    String idString = input.nextLine();
                    if (idString.matches("^\\d*$")) {
                        zooCustomerId = Integer.parseInt(idString);
                        if (repositoryZooCustomer.findZooCustomerById(zooCustomerId) != null) {
                            break;
                        }
                    }
                    System.out.println("Try again, enter only numbers.");
                }
                boolean isValidCustomerType;
                String customerType;
                do {
                    System.out.println("Customer types: [ Bronze / Silver / Gold ]");
                    System.out.print("Customer type: ");
                    customerType = input.nextLine();
                    switch (customerType) {
                        case "Bronze":
                            customerType = "Bronze";
                            isValidCustomerType = true;
                            break;
                        case "Silver":
                            customerType = "Silver";
                            isValidCustomerType = true;
                            break;
                        case "Gold":
                            customerType = "Gold";
                            isValidCustomerType = true;
                            break;
                        default:
                            System.out.println("Not a valid Customer type.");
                            isValidCustomerType = false;
                            break;
                    }
                } while (!isValidCustomerType);
                repositoryZooCustomer.updateZooCustomerType(customerType, zooCustomerId);
                break;

            case "Status":
                repositoryZooCustomer.listAllCustomers();
                while (true) {
                    System.out.print("Enter employee ID to update: ");
                    String idString = input.nextLine();
                    if (idString.matches("^\\d*$")) {
                        zooCustomerId = Integer.parseInt(idString);
                        if (repositoryZooCustomer.findZooCustomerById(zooCustomerId) != null) {
                            break;
                        }
                    }
                    System.out.println("Try again, enter only numbers.");
                }
                repositoryZooCustomer.updateZooCustomerToNotNew(zooCustomerId);
                break;
            default:
                System.out.println("Invalid option, try again.");
                break;
        }
    }

    public void listCustomersByZooName() {
        System.out.println(repositoryZooCustomer.listCustomersByZoo());
    }

    public Ticket getMostCommonTicket() {
        System.out.println("Most common ticket is: " + repositoryZooCustomer.getMostCommonTicket());
        return repositoryZooCustomer.getMostCommonTicket();
    }
}
