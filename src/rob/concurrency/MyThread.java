package rob.concurrency;

public class MyThread extends Thread {
    String name;
    SynchronizedExample s;

    public MyThread(String name, SynchronizedExample s)  {
        this.name = name;
        this.s = s;
        start();
    }

    @Override
    public void run()
    {
        s.printThread3Times(name);
    }
}
