package diningPhilosophers;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Runnable r = new Philosopher("ahaha");
        Thread t = new Thread(r);
        t.start();
        System.out.println( "Hello World! ");
    }
}
