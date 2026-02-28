package model;


public  class Testresult{
    private int size;
    private long sortingexecutiontime;
    private long searchingexecutetime;
     private int searchingindex;

    public Testresult(int size ,long sortingexecutiontime,long searchingexecutetime, int searchingindex){
        this.size=size;
        this.searchingexecutetime=searchingexecutetime;
        this.sortingexecutiontime=sortingexecutiontime;
        this.searchingindex=searchingindex;
        //// System.out.println("DEBUG: Testresult created!");

    }
    public int getsize(){
        return size;
    }
    public long getsortingexecutiontime(){
        return sortingexecutiontime;
    }
    public long  getsearchingexecutetime(){
        return searchingexecutetime;
    }
     public int getsearchingindex(){
        return searchingindex;
    }



    
}
    
        
    



