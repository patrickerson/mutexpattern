package Tasks;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class GeradorPares extends Thread {
    private Semaphore mutex;
    public int[] KEY;
    public int[] params;



    public GeradorPares(Semaphore mutex, int[] key, int[] params) {
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

    private int pairNumberGenerator(){
        Random r = new Random();
        int randomPair = r.nextInt(8);

        while (randomPair % 2 != 0 ){
            randomPair = r.nextInt(8);
        }
        return randomPair;
    }

    private void insertKey(int key){
        this.KEY[this.params[0]] = key;
        this.params[0] = params[0]+1;
    }

    private void pairCountAdd(){
        this.params[2] = params[2]+1;
    }
    public void run() {

            try {

                while(true) {
                    sleep();
                    int key = pairNumberGenerator();
                    mutex.acquire();
                    if (params[0] < 100) {
                        insertKey(key);
                        pairCountAdd();
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
