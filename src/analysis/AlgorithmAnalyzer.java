
import java.util.Random;
import java.util.Scanner;

public class AlgorithmAnalyzer{

    //Binary search algorithms 
    public static void main(String[] args) {
        
        
 
    // Entering input size    
        Scanner scn = new Scanner (System.in);
        System.out.println("Enter input size: ");
        int n = scn.nextInt();

    
        Random rand = new Random();
        int nums[] = new int [n];
        
        
    
    // fill array with random values
            
            for(int i= 0 ; i< n; i++){
            nums[i]= rand.nextInt(n);
           }
    

        System.out.println("Enter number to search:");
        int target = scn.nextInt();
        
    

        //measure sorting execution time
        long sortingstarttime =System.nanoTime();
        SortingAlgorithm.mergesort(nums, 0, nums.length - 1);
        long sortingendtime = System.nanoTime();
        long sortingexecutiontime = sortingendtime -sortingstarttime;
        


        // measure execution time
        long searchingstarttime = System.nanoTime();

        int searchingresults = SearchingAlgorithm.binarySearch(nums, target);

        long searchingendtime = System.nanoTime();
        long  searchingexecutetime = searchingendtime-searchingstarttime ;
    

        

        //display results
        if (searchingresults!=-1 ){
             System.out.println("index :"+searchingresults  );
             System.out .println("Executing time of binarysearch :"+searchingexecutetime +" ns");

        }
        else{
            System.out.println("value not found in array");
        }
    

    
        scn.close();
        

    }   


   
}