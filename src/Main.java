import Tasks.GeradorPares;
import Tasks.GeradorImpares;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        int[] KEY = new int[100];
        int[] params = new int[3]; // [order, oddCount, pairCount]

        Semaphore mutex = new Semaphore(1);

        GeradorImpares gi1 = new GeradorImpares(mutex, KEY, params);
        GeradorImpares gi2 = new GeradorImpares(mutex, KEY, params);
        GeradorPares gp1 = new GeradorPares(mutex, KEY, params);
        GeradorPares gp2 = new GeradorPares(mutex, KEY, params);

        System.out.println("START");
        gi1.start();
        gi2.start();
        gp1.start();
        gp2.start();


        try {
            gi1.join();
            gp1.join();
            gp2.join();
            gi2.join();

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("\nKEY");
        for (int i=0;i<100;i++){
            System.out.print(KEY[i]);
        }
        System.out.println("\n");
        System.out.println("pair");
        System.out.println(params[2]);

        System.out.println("odd");
        System.out.println(params[1]);
    }
}
