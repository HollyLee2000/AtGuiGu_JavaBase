package chapter10_thread.Thread_communication;
//案例一的实现

class PrintNumber implements Runnable{
    private int number = 1;

    @Override
    public void run() {
        while(true){
            synchronized (this){
                notify(); //一旦执行此方法，就会唤醒被wait()的线程中优先级最高的那一个线程，但被叫醒的线程还拿不到这个锁，因为这个线程已经拿到了
                if(number<=100){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+number);
                    number++;

                    try {
                        wait();  //线程一旦执行此方法，就进入等待，同时释放对同步监视器的调用
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}

public class PrintNumberTest {
    public static void main(String[] args) {
        PrintNumber p = new PrintNumber();
        Thread  t1 = new Thread(p, "线程1");
        Thread  t2 = new Thread(p, "线程2");
        t1.start();
        t2.start();
    }
}
