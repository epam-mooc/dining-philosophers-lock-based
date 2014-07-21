package diningPhilosophers;

/**
 * Hello world!
 *
 */
public class App
{
    public static final int PHILOSOPHERS_COUNT = 3;

    public static void main( String[] args ) {
        Fork[] forks = new Fork[PHILOSOPHERS_COUNT];

        for (int i = 0; i < PHILOSOPHERS_COUNT; i++) {
            Fork fork = new Fork();
            forks[i] = fork;
        }

        Thread socrates = new Thread(new Philosopher("Socrates", forks));
        Thread aristotle = new Thread(new Philosopher("Aristotle", forks));
        Thread pifagor = new Thread(new Philosopher("Pifagor", forks));
//        Thread platon = new Thread(new Philosopher("Platon", forks));
//        Thread ptolemy = new Thread(new Philosopher("Ptolemy", forks));

        socrates.start();
        aristotle.start();
        pifagor.start();
//        platon.start();
//        ptolemy.start();
    }
}
