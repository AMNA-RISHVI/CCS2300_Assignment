import java.util.Scanner;

public class AlgorithmAnalyzer{

    //Binary search algorithms 
    public static void main(String[] args) {
        
 

        Scanner scn = new Scanner (System.in);
        System.out.println("Enter input size: ");
        int n = scn.nextInt();


        System.out.println("Enter number to search:");
        int target = scn.nextInt();
        

        // filling array with sorted values
        int nums[] = new int [n];
         for(int i= 0 ; i< nums.length; i++){
            nums[i]= i;
        }

        //measure sorting execution time
        long sortingstarttime =System.nanoTime();
        SortingAlgorithm.mergesort(nums, 0, nums.length - 1);
        long sortingendtime = System.nanoTime();

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