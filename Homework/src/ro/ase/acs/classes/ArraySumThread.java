package ro.ase.acs.classes;

public class ArraySumThread extends Thread{
    private int[] array;
    private int startIndex;
    private int stopIndex;
    public long sum;

    public ArraySumThread(int[] array, int startIndex, int stopIndex){
        this.array = array;
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
    }

    @Override
    public void run() {
        super.run();
        for(int i = startIndex; i < stopIndex; i++){
            sum += array[i];
        }
    }

    public long getSum(){
        return sum;
    }
    
}
