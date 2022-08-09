package Tasks;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GeradorImpares extends Thread {
    private Semaphore mutex;
    public int[] KEY;
    public int[] params;

    public GeradorImpares(Semaphore mutex, int[] key, int[] params) {
        this.mutex = mutex;
        this.KEY = key;
        this.params = params;
    }
    private void sleep(){
        Random r = new Random();
        int randomNumber = r.nextInt(30) + 10;
        try {
            Thread.sleep(randomNumber);
        } catch (Exception e) {

        }

    }

    private int oddNumberGenerator(){
        Random r = new Random();
        int randomOdd = r.nextInt(9);

        while (randomOdd % 2 != 1 ){
            randomOdd = r.nextInt(9);
        }
        return randomOdd;
    }

    private void insertKey(int key){
        this.KEY[this.params[0]] = key;
        this.params[0] = params[0]+1;
    }

    private void oddCountAdd(){
        this.params[1] = params[1]+1;
    }
    public void run(){

            try {



                while(true) {
                    sleep();
                    int key = oddNumberGenerator();
                    mutex.acquire();
                    if (params[0] < 100) {
                        insertKey(key);
                        oddCountAdd();
                        mutex.release();
                    }else{
                        mutex.release();
                        break;
                    }




                }


            } catch (InterruptedException e) {

                    throw new RuntimeException(e);

            }

    }

}
