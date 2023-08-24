package chapter10_thread.Implement_runnable;

class EvenRunner implements Runnable{
    @Override
    public void run() {
        for(int i=1; i<=100; i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}
public class EvenNumberTest {
    public static void main(String[] args) {
        EvenRunner r1 = new EvenRunner();
        Thread t1 = new Thread((r1));
        Thread t2 = new Thread((r1));
        t1.start();
        t2.start();
    }
}
