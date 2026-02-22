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

        
        // measure execution time
        long starttime = System.nanoTime();

        int results = SearchingAlgorithm.binarySearch(nums, target);

        long endtime = System.nanoTime();
        long  executetime = endtime-starttime ;

        //display results
        if (results!=-1 ){
             System.out.println("index :"+results  );
             System.out .println("Executing time of binarysearch :"+executetime +" ns");

        }
        else{
            System.out.println("value not found in array");
        }


        scn.close();
        

    }   


   
}