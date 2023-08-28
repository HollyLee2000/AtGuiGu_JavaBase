package chapter10_thread.Thread_safe;

//使用实现Runnable接口的方式实现卖票，使用同步方法实现Runnable接口的线程安全问题

class SaleTicketFunction implements Runnable{
    int ticket = 100;
    boolean flag = true;
    @Override
    public void run() {
        while(flag){
            //睡一下是方便其他窗口抢到这个资源，不然有可能下一时刻还是这个窗口抢到并卖票，这个跟cpu有关
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    public synchronized void sale(){  //对于非静态的同步方法，同步监视器都为固定的this，无法修改
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
            ticket--;
        }else{
            flag = false;
        }
    }
}

public class WindowTestRunnableFunction {
    public static void main(String[] args) {
        SaleTicketFunction s1 = new SaleTicketFunction();
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