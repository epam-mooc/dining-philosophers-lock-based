package diningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmytro_veres on 20.07.14.
 */
public class Fork {
    private static int maxUid = 0;

    private final int id;
    private Lock lock;

    public Fork() {
        id = maxUid++;
        lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
