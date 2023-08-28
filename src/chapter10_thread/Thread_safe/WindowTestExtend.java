package chapter10_thread.Thread_safe;

//使用继承Thread类的方式实现卖票，，使用同步代码块解决线程不安全的问题

class SaleTicketExtend extends Thread{
    static int ticket = 100;
    static Object obj = new Object();
    @Override
    public void run() {
        while(true){
            //睡一下是方便其他窗口抢到这个资源，不然有可能下一时刻还是这个窗口抢到并卖票，这个跟cpu有关
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果this(当前的对象)是唯一的，这里也可以使用synchronized(this)，但是这里不可以

//            synchronized(SaleTicketExtend.class){  //超纲内容: Class clz = SaleTicketExtend.class，这个对象是这个类本身
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
