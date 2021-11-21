package menu;

import model.Employee;
import persistence.RepositoryEmployee;
import persistence.RepositoryZoo;

import java.util.Scanner;

public class EmployeeMenu {

    RepositoryEmployee repositoryEmployee = new RepositoryEmployee();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add employees");
        System.out.println("2: Remove employees");
        System.out.println("3: Update employee by ID");
        System.out.println("4: List all employees");
        System.out.println("5: Find employee by ID");
        System.out.println("6: Assign employee to zoo");
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
                    addEmployeeMenu(input, repositoryEmployee);
                    break;
                case 2:
                    deleteEmployeeMenu(input);
                    break;
                case 3:
                    updateEmployee(input);
                    break;
                case 4:
                    listEmployees(repositoryEmployee);
                    break;
                case 5:
                    findEmployeeById(input);
                    break;
                case 6:
                    assignEmployeeToZoo(input);
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

    public void addEmployeeMenu(Scanner input, RepositoryEmployee repositoryEmployee) {
        input.nextLine();
        String title;
        do {
            System.out.println("Employee title only has letters from a-Z");
            System.out.print("Employee title: ");
            title = input.nextLine();
        } while (!title.matches("[a-zA-Z]{3,20}$+"));

        String name;
        do {
            System.out.println("Employee name only has letters from a-Z");
            System.out.print("Employee name: ");
            name = input.nextLine();
        } while (!name.matches("[a-zA-Z]{3,30}$+"));

        int salary;
        while (true) {
            System.out.println("Enter salary: ");
            String salaryString = input.nextLine();
            if (salaryString.matches("^\\d*$")) {
                salary = Integer.parseInt(salaryString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        Employee employee = new Employee(title, name, salary);
        repositoryEmployee.addEmployee(employee);
    }

    public void findEmployeeById(Scanner input) {
        input.nextLine();
        int employeeId;
        while (true) {
            System.out.print("Enter cage ID to find: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                employeeId = Integer.parseInt(idString);
                break;
            }
            System.out.println("Try again, enter only numbers.");
        }
        repositoryEmployee.findEmployeeById(employeeId);
    }
    // Method to add employee to a zoo


    public void listEmployees(RepositoryEmployee repositoryEmployee) {
        System.out.println(repositoryEmployee.listAllEmployees());
    }

    public void deleteEmployeeMenu(Scanner input) {
        input.nextLine();
        int employeeId;
        while (true) {
            System.out.print("Enter employee ID to delete: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                employeeId = Integer.parseInt(idString);
                if (repositoryEmployee.findEmployeeById(employeeId) == null) {
                    System.out.println("Didn't find an animal with ID: " + employeeId);
                } else {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        Employee employee = repositoryEmployee.findEmployeeById(employeeId);
        repositoryEmployee.deleteEmployee(employee);
    }

    public void updateEmployee(Scanner input) {
        input.nextLine();
            int employeeId;
            System.out.println("What do you want to update? [ Name / Title / Salary ]");
            System.out.print("Update: ");
            String choice = input.nextLine();
            switch (choice) {
                case "Name":
                    repositoryEmployee.listAllEmployees();
                    while (true) {
                        System.out.print("Enter employee ID to update: ");
                        String idString = input.nextLine();
                        if (idString.matches("^\\d*$")) {
                            employeeId = Integer.parseInt(idString);
                            if (repositoryEmployee.findEmployeeById(employeeId) != null) {
                                break;
                            }
                        }
                        System.out.println("Try again, enter only numbers.");
                    }
                    String newEmployeeName;
                    do {
                        System.out.println("Name only has letters from a-Z");
                        System.out.print("Enter new name: ");
                        newEmployeeName = input.nextLine();
                    } while (!newEmployeeName.matches("[a-zA-Z]+"));
                    repositoryEmployee.updateEmployeeName(newEmployeeName, employeeId);
                    break;

                case "Title":
                    repositoryEmployee.listAllEmployees();
                    while (true) {
                        System.out.print("Enter employee ID to update: ");
                        String idString = input.nextLine();
                        if (idString.matches("^\\d*$")) {
                            employeeId = Integer.parseInt(idString);
                            if (repositoryEmployee.findEmployeeById(employeeId) != null) {
                                break;
                            }
                        }
                        System.out.println("Try again, enter only numbers.");
                    }
                    String newEmployeeTitle;
                    do {
                        System.out.println("Title only has letters from a-Z");
                        System.out.print("Enter new title: ");
                        newEmployeeTitle = input.nextLine();
                    } while (!newEmployeeTitle.matches("[a-zA-Z]+"));
                    repositoryEmployee.updateEmployeeTitle(newEmployeeTitle, employeeId);
                    break;

                case "Salary":
                    repositoryEmployee.listAllEmployees();
                    while (true) {
                        System.out.print("Enter employee ID to update: ");
                        String idString = input.nextLine();
                        if (idString.matches("^\\d*$")) {
                            employeeId = Integer.parseInt(idString);
                            if (repositoryEmployee.findEmployeeById(employeeId) != null) {
                                break;
                            }
                        }
                        System.out.println("Try again, enter only numbers.");
                    }
                    int newEmployeeSalary;
                    while (true) {
                        System.out.print("New salary: ");
                        String intString = input.nextLine();
                        if (intString.matches("^\\d*$")) {
                            newEmployeeSalary = Integer.parseInt(intString);
                            break;
                        }
                        System.out.println("Try again, enter only numbers.");
                    }
                    repositoryEmployee.updateEmployeeSalary(employeeId, newEmployeeSalary);
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
        }
    }

    public void assignEmployeeToZoo(Scanner input) {
        input.nextLine();
        int employeeId;
        RepositoryZoo repositoryZoo = new RepositoryZoo();

        while (true) {
            System.out.print("Enter employee ID to assign Zoo to: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                employeeId = Integer.parseInt(idString);
                if (repositoryEmployee.findEmployeeById(employeeId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }

        int zooId;
        while (true) {
            System.out.print("Enter zoo ID to give: ");
            String idString = input.nextLine();
            if (idString.matches("^\\d*$")) {
                zooId = Integer.parseInt(idString);
                if (repositoryZoo.findZooById(zooId) != null) {
                    break;
                }
            }
            System.out.println("Try again, enter only numbers.");
        }
        repositoryEmployee.assignEmployeeToZoo(employeeId, zooId);
    }
}
