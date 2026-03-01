package module3.analysis;

import java.util.Random;
import java.util.Scanner;

import module3.model.Testresult;

public class AlgorithmAnalyzer{

    


    // This method can be called from your menu
    public static void  runAnalyzer(int size , Scanner scn){

         int n = size;
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
        SortingAlgorithm.mergesort(nums,0,nums.length - 1);
        long sortingendtime = System.nanoTime();
        long sortingexecutiontime = sortingendtime -sortingstarttime;
        


        // measure execution time
        long searchingstarttime = System.nanoTime();

        int searchingindex =SearchingAlgorithm.binarySearch(nums, target);

        long searchingendtime = System.nanoTime();
        long  searchingexecutetime = searchingendtime-searchingstarttime ;
    
     //table header create
         System.out.println("\n=======================================================================");
         System.out.println("...................Execution time Table.................................");
         System.out.println("=========================================================================");
         System.out.printf( "%-15s %-12s %-15s\n",
                           "Inputsize",
                              " Mergesorting(ns)",
                              " binarysearching(ns)"
                              
       );

         System.out.println("------------------------------------------------------------------------");

        

        //display results on table 
        Testresult result = new Testresult(
                            size,
                            sortingexecutiontime,
                            searchingexecutetime,
                            searchingindex);
    
        System.out.printf("%-15d %,15d %,15d%n",
                        result.getsize(),
                        result.getsortingexecutiontime(),
                       result.getsearchingexecutetime());
                        
         System.out.println("------------------------------------------------------------------------");




        if (searchingindex!=-1){
             System.out.println("index :"+searchingindex);
            

        }
        else{
            System.out.println("value not found in array");
        }
        

    }
    // optional main() for testing
    public static void main(String[] args) {
        // Entering input size    
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter input size: ");
        int size = sc.nextInt();
        runAnalyzer(size,sc);

        
    }



   
}