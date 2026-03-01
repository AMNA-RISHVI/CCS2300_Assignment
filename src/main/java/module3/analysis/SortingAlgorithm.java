package module3.analysis;
public class SortingAlgorithm{
    public static void mergesort(int []nums ,int l,int r ){
        if(l<r){

            int mid = (l+r)/2;
            mergesort(nums, l, mid);
            mergesort(nums,mid+1,r);
            merge(nums,l,mid,r);

        }


    }
    private static void merge (int []nums ,int l, int mid ,int r){
        
        int n1 =mid-l +1;
        int n2 = r- mid;


        int lNums[] =new int[n1];
        int rNums[] = new int[n2];

        for(int x=0;x<n1;x++){
            lNums[x]=nums[l+x];
      
        }
        for(int x=0;x<n2;x++){
            rNums[x]=nums[mid+1+x];
      
        }
        int i=0;
        int j=0;
        int k=l;
        while(i < n1 && j<n2){
            if (lNums[i]<= rNums[j]){
               nums[k]=lNums[i];
               i++; 
            }
            else{
                nums[k]=rNums[j];
                j++;
            }
            k++;

        }
        while (i<n1){
            nums[k]=lNums[i];
            i++;
            k++;
        }
         while (j<n2){
            nums[k]=rNums[j];
            j++;
            k++;
        }





    }


}
    
    


