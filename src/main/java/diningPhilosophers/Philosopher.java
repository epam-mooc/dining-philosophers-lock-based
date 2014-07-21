package diningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmytro_veres on 20.07.14.
 */
public class Philosopher implements Runnable {

    public static final long THINK_TIME = 500;
    public static final long EAT_TIME = 300;

    private static int maxUid = 1;

    private final int id;
    private String name;
    private Fork[] forks;

    public Philosopher(String name, Fork[] forks) {
        this.id = maxUid++;
        this.name = name;
        this.forks = forks;
    }

    @Override
    public void run() {
        Fork firstFork = forks[id];
        Fork secondFork = forks[(id + 1) % forks.length];

        Lock firstForkLock = firstFork.getLock();
        Lock secondForkLock = secondFork.getLock();
        firstForkLock.lock();
        try {
            secondForkLock.lock();
            try {
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                secondForkLock.unlock();
            }
        } finally {
            firstForkLock.unlock();
        }
    }

    private void eat() throws InterruptedException {
        System.out.println(name + " is eating.");
        Thread.sleep(EAT_TIME);
    }

    private void think() throws InterruptedException {
        System.out.println(name + " is thinking.");
        Thread.sleep(THINK_TIME);
    }

    public static int getMaxUid() {
        return maxUid;
    }

    public static void setMaxUid(int maxUid) {
        Philosopher.maxUid = maxUid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fork[] getForks() {
        return forks;
    }

    public void setForks(Fork[] forks) {
        this.forks = forks;
    }
}
