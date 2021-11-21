package menu;

import util.DBUtil;

import java.util.Scanner;

public class SubMenuOptions {
    boolean exit = false;
    private MenuCustomer menuCustomer;
    private AnimalMenu animalMenu;
    private EmployeeMenu employeeMenu;
    private CageMenu cageMenu;
    private ZooCustomerMenu zooCustomerMenu;
    private TicketMenu ticketMenu;
    private ZooMenu zooMenu;

    public SubMenuOptions() {
        this.menuCustomer = new MenuCustomer();
        this.animalMenu = new AnimalMenu();
        this.employeeMenu = new EmployeeMenu();
        this.cageMenu = new CageMenu();
        this.zooCustomerMenu = new ZooCustomerMenu();
        this.ticketMenu = new TicketMenu();
        this.zooMenu = new ZooMenu();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Main menu ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Sub Menu - Customer // Not mine");
        System.out.println("2: Sub Menu - Delivery // Not mine");
        System.out.println("3: Sub Menu - Animals");
        System.out.println("4: Sub Menu - Employees");
        System.out.println("5: Sub Menu - Cages");
        System.out.println("6: Sub Menu - ZooCustomers");
        System.out.println("7: Sub Menu - Tickets");
        System.out.println("8: Sub Menu - Zoos");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }

    public void menuChoice(Scanner input) {
        while(!exit) {
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    this.menuCustomer.menuChoice(input);
                    break;
                case 2:
                    break;
                case 3:
                    this.animalMenu.menuChoice(input);
                    break;
                case 4:
                    this.employeeMenu.menuChoice(input);
                    break;
                case 5:
                    this.cageMenu.menuChoice(input);
                    break;
                case 6:
                    this.zooCustomerMenu.menuChoice(input);
                    break;
                case 7:
                    this.ticketMenu.menuChoice(input);
                    break;
                case 8:
                    this.zooMenu.menuChoice(input);
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 100:
                    exit = true;
                    System.out.println("System closed");
                    break;
//                default:
//                    System.out.println("\nSorry, please enter valid Option");
//                    menuChoice(input);
            }
        }
    }
}
