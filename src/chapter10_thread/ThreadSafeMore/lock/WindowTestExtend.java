package chapter10_thread.ThreadSafeMore.lock;

//Lock属于JUC(java.util.concurrent)的内容

import java.util.concurrent.locks.ReentrantLock;

class SaleTicketExtend extends Thread{
    static int ticket = 100;
    //1.创建Lock的实例，确保多个线程共用同一个Lock实例，所以要加static
    private static final ReentrantLock lock = new ReentrantLock();  //多个线程公用一个lock，所以要声明为static

    @Override
    public void run() {
        while(true){
            try{
                //2.执行lock()方法，锁定对共享资源的调用
                lock.lock();
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                //3.unlock()的调用，释放对共享数据的锁定
                lock.unlock();
            }
        }
    }
}

public class WindowTestExtend {
    public static void main(String[] args) {
        SaleTicketExtend t1 = new SaleTicketExtend();
        SaleTicketExtend t2 = new SaleTicketExtend();
        SaleTicketExtend t3 = new SaleTicketExtend();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
