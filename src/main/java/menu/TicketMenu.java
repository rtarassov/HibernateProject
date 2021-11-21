package menu;

import model.Ticket;
import persistence.RepositoryTicket;
import persistence.RepositoryZooCustomer;

import java.util.Scanner;

public class TicketMenu {
    RepositoryTicket repositoryTicket = new RepositoryTicket();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add Ticket");
        System.out.println("2: Remove Ticket");
        System.out.println("3: Update Ticket by ID // NOT IMPLEMENTED");
        System.out.println("4: List all Tickets");
        System.out.println("5: Find Ticket by ID");
        System.out.println("6: Number of Tickets");
        System.out.println("7: Assign ticket");

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
                    addTicketMenu(input);
                    break;
                case 2:
                    deleteTicketMenu(input);
                    break;
                case 3:
                    // update
                    break;
                case 4:
                    listAllTicketsMenu();
                    break;
                case 5:
                    findTicketByIdMenu(input);
                    break;
                case 6:
                    numberOfTickets();
                    break;
                case 7:
                    assignTicketToCustomerMenu(input);
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

    public void addTicketMenu(Scanner input) {
        input.nextLine();
        String ticketType;
        boolean isValidTicketType = false;

        do {
            System.out.println("Ticket types: [ Single / Family / Adult / Child ]");
            System.out.print("Ticket type: ");
            ticketType = input.nextLine();
            switch (ticketType) {
                case "Single":
                case "Family":
                case "Adult":
                case "Child":
                    isValidTicketType = true;
                    break;
                default:
                    break;
            }
        } while (!isValidTicketType);

        Ticket ticket = new Ticket(ticketType, false);
        repositoryTicket.addTicket(ticket);
    }

    public void findTicketByIdMenu(Scanner input) {
        input.nextLine();
        int ticketId;
        while (true) {
            System.out.println("Enter Ticket ID: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                ticketId = Integer.parseInt(idString);
                if (repositoryTicket.findTicketById(ticketId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        System.out.println(repositoryTicket.findTicketById(ticketId));
    }

    public void listAllTicketsMenu() {
        System.out.println(repositoryTicket.listAllTickets());
    }
    public void numberOfTickets() {
        System.out.println(repositoryTicket.listAllTickets().size());
    }

    public void deleteTicketMenu(Scanner input) {
        input.nextLine();
        int ticketId;

        while (true) {
            System.out.println("Enter Ticket ID: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                ticketId = Integer.parseInt(idString);
                if (repositoryTicket.findTicketById(ticketId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        Ticket ticket = repositoryTicket.findTicketById(ticketId);
        repositoryTicket.deleteTicket(ticket);
    }

    public void assignTicketToCustomerMenu(Scanner input) {
        input.nextLine();
        RepositoryZooCustomer repositoryZooCustomer = new RepositoryZooCustomer();
        int customerId;
        while (true) {
            System.out.print("Enter customer ID to give ticket to: ");
            String customerIdString = input.nextLine();
            if (customerIdString.matches("^\\d*$")) {
                customerId = Integer.parseInt(customerIdString);
                if (repositoryZooCustomer.findZooCustomerById(customerId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        int ticketId;
        while (true) {
            System.out.print("Enter ticket ID to give: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                ticketId = Integer.parseInt(idString);
                if (repositoryTicket.findTicketById(ticketId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        repositoryTicket.findTicketById(ticketId).setValid(true);
        repositoryTicket.assignTicketToCustomer(customerId, ticketId);
    }
}
