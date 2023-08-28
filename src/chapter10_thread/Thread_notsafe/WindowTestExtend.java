package chapter10_thread.Thread_notsafe;

//使用继承Thread类的方式实现卖票，但是不安全

class SaleTicketExtend extends Thread{
    static int ticket = 100;
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
