import java.util.Scanner;

public class PageNavigationUpdated {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner); // Start the navigation from the Main Menu
    }

    // Method for the Main Menu
    public static void mainMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Go to Page 1");
            System.out.println("2. Go to Page 3");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                page1(scanner); // Navigate to Page 1
            } else if (choice == 2) {
                page3(scanner); // Navigate to Page 3 directly
            } else if (choice == 3) {
                System.out.println("Exiting the program...");
                System.exit(0); // Exit the program
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method for Page 1
    public static void page1(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Page 1 ---");
            System.out.println("1. Go to Page 2");
            System.out.println("2. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                page2(scanner); // Navigate to Page 2
                return;         // Exit Page 1 after going to Page 2
            } else if (choice == 2) {
                return;         // Return to Main Menu directly
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method for Page 2
    public static void page2(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Page 2 ---");
            System.out.println("1. Go to Page 3");
            System.out.println("2. Return to Page 1");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                page3(scanner); // Navigate to Page 3
                return;         // Exit Page 2 after going to Page 3
            } else if (choice == 2) {
                return;         // Return to Page 1 directly
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method for Page 3
    public static void page3(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Page 3 ---");
            System.out.println("1. Return to Main Menu");
            System.out.println("2. Return to Page 2 (if accessed from there)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                return; // Return to Main Menu directly
            } else if (choice == 2) {
                page2(scanner); // Return to Page 2 (if accessed from there)
                return;         // Exit Page 3 after going to Page 2
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
