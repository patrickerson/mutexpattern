package Tasks;

import java.util.concurrent.Semaphore;

public class Task {
    int[] KEY = new int[100];
    int order = 5;
    int oddCount = 0;
    int pairCount = 0;
    Semaphore mutex = new Semaphore(1);


}
