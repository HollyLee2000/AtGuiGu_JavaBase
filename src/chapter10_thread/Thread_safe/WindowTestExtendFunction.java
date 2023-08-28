package chapter10_thread.Thread_safe;


class SaleTicketExtendFunction extends Thread{
    static int ticket = 100;
    static boolean flag = true;
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

    public static synchronized void sale(){    //对于静态的同步方法，同步监视器都为固定的当前类本身，无法修改
        // 对于继承的方式，只有静态的同步方法能达到线程安全的要求，但是不要刻意地去使用，如果此方法中用到了实例变量，就只能用同步代码块了
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
            ticket--;
        }else{
            flag = false;
        }
    }
}

public class WindowTestExtendFunction {
    public static void main(String[] args) {
        SaleTicketExtendFunction t1 = new SaleTicketExtendFunction();
        SaleTicketExtendFunction t2 = new SaleTicketExtendFunction();
        SaleTicketExtendFunction t3 = new SaleTicketExtendFunction();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}