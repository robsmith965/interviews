package rob.concurrency;

public class SynchronizedExample {
   
   /*
    * without synchronized, it's faster, and prints in unpredictable order.
    * with synchronized, it's slower, prints 111222.
    *
    */
    public synchronized void printThread3Times(String name)
    {
        for (int i = 0; i < 3; i++) {
           System.out.println(name + " :: " + i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
       SynchronizedExample s = new SynchronizedExample();
        new MyThread("THREAD 1",s);
        new MyThread("THREAD 2",s);
    }
}
