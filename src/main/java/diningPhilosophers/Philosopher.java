package diningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmytro_veres on 20.07.14.
 */
public class Philosopher implements Runnable {

    public static final long THINK_TIME = 500;
    public static final long EAT_TIME = 300;

    private static int maxUid = 0;

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
        while(true) {
            Fork firstFork, secondFork;

            if(id < ((id + 1) % forks.length)) {
                firstFork = forks[id];
                secondFork = forks[(id + 1) % forks.length];
            } else {
                firstFork = forks[(id + 1) % forks.length];
                secondFork = forks[id];
            }

            Lock firstForkLock = firstFork.getLock();
            Lock secondForkLock = secondFork.getLock();
            firstForkLock.lock();
            System.out.println(name + " takes right fork");
            try {
                secondForkLock.lock();
                System.out.println(name + " takes left fork");
                try {
                    eat();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    secondForkLock.unlock();
                    System.out.println(name + " drops left fork");
                }
            } finally {
                firstForkLock.unlock();
                System.out.println(name + " drops right fork");
            }

            try {
                think();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
