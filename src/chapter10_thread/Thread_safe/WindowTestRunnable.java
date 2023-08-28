package chapter10_thread.Thread_safe;

//使用实现Runnable接口的方式实现卖票，使用同步代码块解决线程不安全的问题

class SaleTicket implements Runnable{
    int ticket = 100;
    Object obj =  new Object();
    @Override
    public void run() {
        //把synchronized放到这里也是线程安全的，但是当一个线程抢到锁以后就会进入while(true)，卖完票了才释放锁
//        synchronized(obj){
        while(true){
            //睡一下是方便其他窗口抢到这个资源，不然有可能下一时刻还是这个窗口抢到并卖票，这个跟cpu有关
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果this(当前的对象)是唯一的，这里也可以使用synchronized(this)，比如这里就可以
            synchronized(obj){
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
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
