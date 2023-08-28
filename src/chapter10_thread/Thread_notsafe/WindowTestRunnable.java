package chapter10_thread.Thread_notsafe;

//使用实现Runnable接口的方式实现卖票，但是不安全

class SaleTicket implements Runnable{
    int ticket = 100;
    @Override
    public void run() {
        while(true){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}

public class WindowTestRunnable {
    public static void main(String[] args) {
        SaleTicket s1 = new SaleTicket();
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s1);
        Thread t3 = new Thread(s1);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

}
