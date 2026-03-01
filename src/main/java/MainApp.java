import module1.menu.RoutePlannerMenu;
import module_2.SortingMenu;
import module3.menu.PerformanceAnalyzerMenu;
import java.util.Scanner;

//Displays a main menu to select one of the three modules.
 
public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "*".repeat(50));
        System.out.println("   SMART CITY MANAGEMENT SYSTEM");
        System.out.println("*".repeat(50));

        while (true) {
            printMainMenu();
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Module 1: Smart City Route Planner
                    RoutePlannerMenu routePlanner = new RoutePlannerMenu();
                    routePlanner.displayMainMenu();
                    break;
                case "2":
                    // Module 2: Data Sorter
                    SortingMenu sortingMenu = new SortingMenu();
                    sortingMenu.start();
                    break;
                case "3":
                    // Module 3: Algorithm Performance Analyzer
                    // Create a fresh scanner for this module
                    Scanner module3Scanner = new Scanner(System.in);
                    PerformanceAnalyzerMenu analyzerMenu = new PerformanceAnalyzerMenu();
                    analyzerMenu.showMenu(module3Scanner);
                    break;
                case "4":
                    System.out.println("\nThank you for using the Smart City Management System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }


    //Prints the main menu options.
    private static void printMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Smart City Route Planner (Module 1)");
        System.out.println("2. Data Sorter (Module 2)");
        System.out.println("3. lgorithm Performance Analyzer (Module 3)");
        System.out.println("4. Exit");
        System.out.println("=".repeat(50));
    }
}