package chapter10_thread.practice_deposit;

//题目：两个储户分别向同一个账户存3000元，每次1000存3次，每次存完打印账户余额
//这里使用继承Thread类

class AccountRunnable{
    private double money;
    AccountRunnable(double money){
        this.money = money;
    }
    //这个案例中因为只有一个账户，所以可以使用this
    //方法1，使用同步方法
    public synchronized void deposite(double mny){
        this.money += mny;
        System.out.println(Thread.currentThread().getName()+"存了钱，余额为"+money+"元");
    }
    //方法1，使用同步代码块
//    public void deposite(double mny){
//        synchronized (this){
//            this.money += mny;
//            System.out.println(Thread.currentThread().getName()+"存了钱，余额为"+money+"元");
//        }
//    }
}

class CoustomerRunnable implements Runnable{
    private AccountRunnable acc;
    public CoustomerRunnable(AccountRunnable acc){
        this.acc = acc;
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            //睡一下增加其他进程进来的机会
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            acc.deposite(1000);
        }
    }
}

public class DepositRunnable {
    public static void main(String[] args) {
        AccountRunnable acc = new AccountRunnable(0);
        CoustomerRunnable cos1 = new CoustomerRunnable(acc);
        CoustomerRunnable cos2 = new CoustomerRunnable(acc);
        Thread t1 = new Thread(cos1);
        t1.setName("甲");
        Thread t2 = new Thread(cos2);
        t2.setName("乙");
        t1.start();
        t2.start();
    }
}
