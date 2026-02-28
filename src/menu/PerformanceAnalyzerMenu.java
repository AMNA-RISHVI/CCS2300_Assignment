package menu;
import analysis.AlgorithmAnalyzer;
import java.util.Scanner;

public class performaceAnalyzerMenu {
    public  void showMenu(Scanner sc ){
        
        int choice;
        do {
            System.out.println("=====================Performance Analyzer Menu==================");
            System.out.println("1.Run Analyzer");
            System.out.println("2.Exit");
            System.out.print("Enter your choice:");
            
            choice=sc.nextInt();
             
            switch(choice){
                case 1:
                    System.out.println("Enter input size: ");
                    int size = sc. nextInt();

                    //call your analyzer
                    AlgorithmAnalyzer.runAnalyzer(size,sc);
                    break;
                case 2:
                    System.out.println("Exiting program");
                    break;
    
                default:
                    System.out.println("Invalide choice try again");

            }


            
        } while (choice !=2);

        

    }
    
}
